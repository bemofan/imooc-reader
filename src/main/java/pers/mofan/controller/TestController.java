package pers.mofan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mofan
 * @date 2021/1/8 11:01 下午
 */
@Controller
public class TestController {

    @GetMapping("/test/t1")
    public ModelAndView t1() {
        return new ModelAndView("test");
    }

    @GetMapping("/test/t2")
    @ResponseBody
    public Map<String, String> t2() {
        Map<String, String> result = new HashMap(6);
        result.put("test", "测试文本");
        return result;
    }
}
