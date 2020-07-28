package cn.fxbin.record.mapstruct.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserDetailBO
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:28
 */
@Data
@Accessors(chain = true)
public class UserDetailBO {

    private Integer userId;

}
