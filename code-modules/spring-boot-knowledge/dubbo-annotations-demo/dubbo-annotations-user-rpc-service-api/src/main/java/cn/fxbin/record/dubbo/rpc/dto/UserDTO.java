package cn.fxbin.record.dubbo.rpc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
    private String name;
    /**
     * 性别
     */
    private Integer gender;

}
