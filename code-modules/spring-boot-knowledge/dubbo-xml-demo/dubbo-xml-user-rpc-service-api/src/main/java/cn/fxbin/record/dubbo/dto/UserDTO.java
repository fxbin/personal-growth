package cn.fxbin.record.dubbo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * UserDTO
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 18:39
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    private String name;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer gender;

}
