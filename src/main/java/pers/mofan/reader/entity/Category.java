package pers.mofan.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2021/1/10 17:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@TableName("category")
public class Category {

    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;
    @TableField("category_name")
    private String categoryName;
}
