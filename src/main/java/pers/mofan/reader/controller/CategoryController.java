package pers.mofan.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.mofan.reader.service.CategoryService;

import javax.annotation.Resource;

/**
 * @author mofan
 * @date 2021/1/10 17:48
 */
@Controller
public class CategoryController {
    @Resource
    private CategoryService categoryService;


    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("categoryList", categoryService.selectAll());
        return mav;
    }
}
