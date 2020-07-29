package cn.fxbin.record.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * EventDemoApplication
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 17:09
 */
@EnableAsync
@SpringBootApplication
public class EventDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventDemoApplication.class, args);
    }

}
