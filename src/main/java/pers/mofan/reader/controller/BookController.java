package pers.mofan.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.mofan.reader.entity.Book;
import pers.mofan.reader.entity.Evaluation;
import pers.mofan.reader.entity.Member;
import pers.mofan.reader.entity.MemberReadState;
import pers.mofan.reader.service.BookService;
import pers.mofan.reader.service.CategoryService;
import pers.mofan.reader.service.EvaluationService;
import pers.mofan.reader.service.MemberService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Resource
    private EvaluationService evaluationService;
    @Resource
    private MemberService memberService;

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

    @GetMapping("/book/{id}")
    public ModelAndView showDetail(@PathVariable("id")Long id, HttpSession session) {
        Book book = bookService.selectById(id);
        List<Evaluation> evaluationList = evaluationService.selectByBookId(id);

        ModelAndView mav = new ModelAndView("/detail");
        Member member= (Member)session.getAttribute("loginMember");
        if (member != null) {
            // 获取会员阅读状态
            MemberReadState memberReadState = memberService.selectMemberReadState(member.getMemberId(), id);
            mav.addObject("memberReadState", memberReadState);
        }
        mav.addObject("book", book);
        mav.addObject("evaluationList",evaluationList);
        return mav;

    }

}
