package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.mofan.reader.entity.Book;
import pers.mofan.reader.mapper.BookMapper;
import pers.mofan.reader.service.BookService;

import javax.annotation.Resource;

/**
 * @author mofan
 * @date 2021/1/11 11:26
 */
@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {

    public static final String QUANTITY = "quantity";
    public static final String SCORE = "score";
    @Resource
    private BookMapper bookMapper;

    @Override
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (categoryId != null && categoryId != -1) {
            queryWrapper.eq("category_id", categoryId);
        }
        if (order != null) {
            if (order.equals(QUANTITY)) {
                queryWrapper.orderByDesc("evaluation_quantity");
            }
            if (order.equals(SCORE)) {
                queryWrapper.orderByDesc("evaluation_score");
            }
        }

        /*
         * mybatis-plus Page对象中page表示页数，rows表示每页的显示条数
         * 例如 new Page<>(2, 3) =》表示的是第二页，每页显示的数据都是3条，包括第一页也是三条数据
         */
        Page<Book> p = new Page<>(page, rows);
        Page<Book> bookPage = bookMapper.selectPage(p, queryWrapper);
        return bookPage;
    }

    @Override
    public Book selectById(Long bookId) {
        return bookMapper.selectById(bookId);
    }
}
