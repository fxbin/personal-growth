package cn.fxbin.record.grpc.config;

import cn.fxbin.record.grpc.userservice.api.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GrpcConfig
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 20:24
 */
@Configuration
public class GrpcConfig {

    private static final Integer GRPC_PORT = 8888;

    @Bean
    public ManagedChannel userGrpcManagedChannel() {
        return ManagedChannelBuilder.forAddress("127.0.0.1", GRPC_PORT).usePlaintext().build();
    }

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceGrpc() {
        // 创建 ManagedChannel 对象
        ManagedChannel userGrpcManagedChannel = this.userGrpcManagedChannel();
        // 创建 UserServiceGrpc 对象
        return UserServiceGrpc.newBlockingStub(userGrpcManagedChannel);
    }

}
