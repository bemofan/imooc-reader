package pers.mofan.reader.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.mofan.reader.entity.Book;
import pers.mofan.reader.service.BookService;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Resource
    private BookService bookService;

    @Test
    public void paging() {
        IPage<Book> paging = bookService.paging(2L, "quantity", 1, 11);

        List<Book> records = paging.getRecords();
        for (Book record : records) {
            System.out.println(record.getBookId() + ":" + record.getBookName());
        }
        System.out.println("总页数:" + paging.getPages());
        System.out.println("总记录数:" + paging.getTotal());
    }
}