package cn.fxbin.record.spring.javaconfig.config;

import cn.fxbin.record.spring.javaconfig.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserConfig
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 11:18
 */
@Configuration
public class UserConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
