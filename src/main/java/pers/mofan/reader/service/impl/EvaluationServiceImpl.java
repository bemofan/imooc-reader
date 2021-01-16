package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mofan.reader.entity.Book;
import pers.mofan.reader.entity.Evaluation;
import pers.mofan.reader.mapper.BookMapper;
import pers.mofan.reader.mapper.EvaluationMapper;
import pers.mofan.reader.mapper.MemberMapper;
import pers.mofan.reader.service.EvaluationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mofan
 * @date 2021/1/16 20:57
 */
@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    private EvaluationMapper evaluationMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("state", "enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);
        evaluationList.forEach(evaluation -> {
            evaluation.setMember(memberMapper.selectById(evaluation.getMemberId()));
            evaluation.setBook(book);
        });
        return evaluationList;
    }
}
