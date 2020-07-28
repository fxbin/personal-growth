package cn.fxbin.record.mapstruct.dataobject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserDO
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:14
 */
@Data
@Accessors(chain = true)
public class UserDO {

    private Integer id;

    private String username;

    private String password;
}
