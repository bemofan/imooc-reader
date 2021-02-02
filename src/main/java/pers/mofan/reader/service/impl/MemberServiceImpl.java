package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mofan.reader.entity.Evaluation;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.entity.MemberReadState;
import pers.mofan.reader.mapper.EvaluationMapper;
import pers.mofan.reader.mapper.MemberMapper;
import pers.mofan.reader.mapper.MemberReadStateMapper;
import pers.mofan.reader.service.MemberService;
import pers.mofan.reader.service.exception.BusinessException;
import pers.mofan.reader.utils.Md5Utils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author mofan
 * @date 2021/1/18 20:17
 */
@Service("memberService")
@Transactional(rollbackFor = BusinessException.class)
public class MemberServiceImpl implements MemberService {


    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    public void createMember(String username, String password, String nickname) {
        QueryWrapper<pers.mofan.reader.entity.Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<pers.mofan.reader.entity.Member> memberList = memberMapper.selectList(queryWrapper);
        if (memberList.size() > 0) {
            throw new BusinessException("M01", "用户名已存在");
        }

        /* 将密码使用md5转换加密 */
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = Md5Utils.md5Digest(password, salt);

        Member member = Member.builder().username(username).password(md5).nickname(nickname).salt(salt).createTime(new Date()).build();
        memberMapper.insert(member);
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new BusinessException("M02", "用户不存在");
        }
        String md5Digest = Md5Utils.md5Digest(password, member.getSalt());
        if (!md5Digest.equals(member.getPassword())) {
            throw new BusinessException("M03", "密码错误");
        }
        return member;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> qw = new QueryWrapper<>();
        qw.eq("book_id", bookId);
        qw.eq("member_id", memberId);
        return memberReadStateMapper.selectOne(qw);
    }

    @Override
    public void updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper<MemberReadState> qw = new QueryWrapper<>();
        qw.eq("book_id", bookId).eq("member_id", memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(qw);
        if (memberReadState == null) {
            memberReadState = MemberReadState.builder().
                    memberId(memberId).bookId(bookId).
                    readState(readState).
                    createTime(new Date()).
                    build();
            memberReadStateMapper.insert(memberReadState);
        } else {
            memberReadState.setReadState(readState);
            memberReadStateMapper.updateById(memberReadState);
        }
    }

    @Override
    public void evaluate(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation = Evaluation.builder()
                .memberId(memberId)
                .bookId(bookId)
                .score(score)
                .content(content)
                .createTime(new Date())
                .state("enable")
                .enjoy(0)
                .build();
        evaluationMapper.insert(evaluation);
    }

    @Override
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() +1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }
}
