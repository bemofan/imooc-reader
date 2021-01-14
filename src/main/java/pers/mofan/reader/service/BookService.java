package pers.mofan.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.mofan.reader.entity.Book;

/**
 * @author mofan
 * @date 2021/1/11 11:24
 */
public interface BookService {

    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows);


    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    Book selectById(Long bookId);


}

