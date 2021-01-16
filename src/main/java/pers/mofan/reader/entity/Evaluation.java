package pers.mofan.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评价
 *
 * @author mofan
 * @date 2021/1/16 19:05
 */


@TableName("evaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evaluation {
    @TableId(type = IdType.AUTO)
    private Long evaluationId;
    private Long bookId;
    private String content;
    private Integer score;
    private Long memberId;
    private Date createTime;
    private Integer enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;
    @TableField(exist = false)
    private Book book;
    @TableField(exist = false)
    private Member member;
}