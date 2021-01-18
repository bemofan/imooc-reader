package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.mapper.MemberMapper;
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
@Transactional
public class MemberServiceImpl implements MemberService {


    @Resource
    private MemberMapper memberMapper;

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
}
