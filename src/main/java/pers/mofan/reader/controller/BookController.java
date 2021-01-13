package pers.mofan.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.mofan.reader.entity.Book;
import pers.mofan.reader.service.BookService;
import pers.mofan.reader.service.CategoryService;

import javax.annotation.Resource;

/**
 * @author mofan
 * @date 2021/1/10 17:48
 */
@Controller
public class BookController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("categoryList", categoryService.selectAll());
        return mav;
    }

    @GetMapping("/books")
    @ResponseBody
    public IPage<Book> selectBook(Long categoryId, String order, Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        return bookService.paging(categoryId, order, pageNumber, 10);
    }
}
