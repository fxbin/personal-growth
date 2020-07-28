package cn.fxbin.record.spring.javaconfig.bean;

import lombok.*;

/**
 * User
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 11:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String password;

}
