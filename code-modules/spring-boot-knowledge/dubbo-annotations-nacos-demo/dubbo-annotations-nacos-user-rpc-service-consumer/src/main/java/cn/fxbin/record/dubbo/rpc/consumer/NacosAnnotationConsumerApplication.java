package cn.fxbin.record.dubbo.rpc.consumer;

import cn.fxbin.record.dubbo.rpc.api.UserRpcService;
import cn.fxbin.record.dubbo.rpc.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * ConsumerApplication
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 18:39
 */
@SpringBootApplication
public class NacosAnnotationConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosAnnotationConsumerApplication.class, args);
    }

    @Component
    public class UserRpcServiceTest implements CommandLineRunner {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @DubboReference(version = "${dubbo.consumer.UserRpcService.version}")
        private UserRpcService userRpcService;

        @Override
        public void run(String... args) throws Exception {
            UserDTO user = userRpcService.get(1);
            logger.info("[run][发起一次 Dubbo RPC 请求，获得用户为({})", user);
        }

    }

}
