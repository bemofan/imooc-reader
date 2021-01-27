package pers.mofan.reader.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.service.MemberService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mofan
 * @date 2021/1/18 20:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceImplTest {
    @Resource
    private MemberService memberService;

    @Test
    public void createMember() {
        memberService.createMember(new Date().toString().substring(11, 19), "S123456", "测试");
    }

    @Test
    public void checkLogin() {
        Member member = memberService.checkLogin("12345678", "12345678");
        System.out.println(member);
    }
}
