package cn.fxbin.record.grpc.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * UserServiceApplication
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 11:13
 */
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserServiceApplication.class, args);
        // 阻塞，避免应用退出
        new CountDownLatch(1).await();
    }

}
