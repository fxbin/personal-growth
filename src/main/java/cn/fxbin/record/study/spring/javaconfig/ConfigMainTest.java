package cn.fxbin.record.study.spring.javaconfig;

import cn.fxbin.record.study.spring.javaconfig.config.UserConfig;
import cn.fxbin.record.study.spring.javaconfig.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ConfigMainTest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 11:19
 */
public class ConfigMainTest {


    public static void main(String[] args) {

        // 获取Java配置类
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(UserConfig.class);
        // 获取 IOC 容器中的对象
        UserService userService = (UserService) context.getBean("userService");
        // 调用方法
        userService.list().forEach(System.out::println);

    }

}
