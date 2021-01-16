package pers.mofan.reader.service;

import pers.mofan.reader.entity.Evaluation;

import java.util.List;

/**
 * 评价
 *
 * @author mofan
 * @date 2021/1/16 20:50
 */
public interface EvaluationService {

    /** 按图书编号查询有效短评
     * @param bookId 图书编号
     * @return 评论列表
     */
    List<Evaluation> selectByBookId(Long bookId);
}
