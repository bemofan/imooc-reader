package pers.mofan.reader.service;

import pers.mofan.reader.entity.Evaluation;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.entity.MemberReadState;

/**
 * 会员
 *
 * @author mofan
 * @date 2021/1/17 22:18
 */
public interface MemberService {
    /**
     * 会员注册，创建会员
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     */
    void createMember(String username, String password, String nickname);

    /**
     * 登录检查
     * @param username 用户名
     * @param password 密码
     * @return 登录对象
     */
    Member checkLogin(String username, String password);

    /**
     * 获取阅读状态
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @return 阅读状态对象
     */
    MemberReadState selectMemberReadState(Long memberId, Long bookId);

    /**
     * 更新阅读状态
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @param readState 阅读状态
     */
    void updateMemberReadState(Long memberId, Long bookId, Integer readState);

    /**
     * 发布新的短评
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @param score 评分
     * @param content 短评内容
     */
    void evaluate(Long memberId, Long bookId, Integer score, String content);

    /**
     * 短评功能
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    Evaluation enjoy(Long evaluationId);
}