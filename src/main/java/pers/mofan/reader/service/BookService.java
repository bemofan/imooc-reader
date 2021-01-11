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
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> paging(Integer page, Integer rows);
}

