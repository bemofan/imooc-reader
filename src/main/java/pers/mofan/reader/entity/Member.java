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
 * 会员
 *
 * @author mofan
 * @date 2021/1/16 21:20
 */
@TableName("member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Member {
    @TableId(type = IdType.AUTO)
    private Long memberId;
    private String username;
    private String password;
    private Integer salt;
    private String nickname;
    private Date createTime;
}
