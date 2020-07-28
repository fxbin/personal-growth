package cn.fxbin.record.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocketConfiguration
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:47
 */
@Configuration
public class WebSocketConfiguration {

    /**
     * 扫描添加有 @ServerEndpoint 注解的 Bean
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
