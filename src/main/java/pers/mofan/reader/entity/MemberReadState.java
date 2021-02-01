package pers.mofan.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 会员阅读状态
 *
 * @author mofan
 * @date 2021/2/1 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("member_read_state")
public class MemberReadState {
    @TableId(type = IdType.AUTO)
    private Long rsId;
    private Long bookId;
    private Long memberId;
    private Integer readState;
    private Date createTime;
}
