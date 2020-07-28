package cn.fxbin.record.study.mapstruct.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserBO
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:15
 */
@Data
@Accessors(chain = true)
public class UserBO {

    private Integer id;

    private String username;

    private String password;

}
