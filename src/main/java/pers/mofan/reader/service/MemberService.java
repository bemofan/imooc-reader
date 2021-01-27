package pers.mofan.reader.service;

import pers.mofan.reader.entity.Member;

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
}