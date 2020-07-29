package cn.fxbin.record.grpc.userservice.config;

import cn.fxbin.record.grpc.userservice.rpc.UserServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * GrpcConfig
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 11:01
 */
@Slf4j
@Configuration
public class GrpcConfig {

    /**
     * gRPC Server 端口
     */
    private static final Integer GRPC_PORT = 8888;

    @Bean
    public Server grpcServer(final UserServiceGrpcImpl userServiceGrpc) throws IOException {

        // 创建 gRPC Server 对象
        Server server = ServerBuilder.forPort(GRPC_PORT)
                .addService(userServiceGrpc)
                .build();
        // 启动 gRPC Server
        server.start();
        log.info("[grpcServer][启动完成，端口为({})]", server.getPort());
        return server;
    }

}
