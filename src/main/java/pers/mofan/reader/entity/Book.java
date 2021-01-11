package pers.mofan.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书
 *
 * @author mofan
 * @date 2021/1/11 11:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("book")
public class Book {
    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;
    private String bookName;
    private String subTitle;
    private String author;
    private String cover;
    private String description;
    private Long categoryId;
    private Float evaluationScore;
    private Integer evaluationQuantity;
}
