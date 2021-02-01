package pers.mofan.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.service.MemberService;
import pers.mofan.reader.service.exception.BusinessException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员
 *
 * @author mofan
 * @date 2021/1/17 21:49
 */
@Controller
public class MemberController {
    @Resource
    MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        return new ModelAndView("/register");
    }


    @GetMapping("/login.html")
    public ModelAndView showLogin() {
        return new ModelAndView("/login");
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> register(String vc, String username, String password, String nickname, HttpServletRequest request) {
        /* 正确验证码 */
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        /* 比较验证码 */
        Map<String, String> result = new HashMap<>(16);
        if (vc == null || !vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                memberService.createMember(username, password, nickname);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BusinessException e) {
                e.printStackTrace();
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }

        }
        return result;
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map<String, String> checkLogin(String username, String password, String vc, HttpSession session) {
        String verifyCode = (String) session.getAttribute("kaptchaVerifyCode");
        Map<String, String> result = new HashMap<>(16);
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember", member);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BusinessException e) {
                e.printStackTrace();
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }
        }
        return result;
    }

    @PostMapping("/update_read_state")
    @ResponseBody
    public Map<String, String> updateReadState(Long memberId, Long bookId, Integer readState) {
        Map<String, String> result = new HashMap<>(16);
        try {
            memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/evaluate")
    @ResponseBody
    public Map<String, String> evaluate(Long memberId, Long bookId, Integer score, String content) {
        Map<String, String> result = new HashMap<>(16);
        try {
            memberService.evaluate(memberId, bookId, score, content);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BusinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
