package cn.fxbin.record.websocket.config;

import cn.fxbin.record.websocket.DemoWebSocketHandler;
import cn.fxbin.record.websocket.interceptor.DemoWebSocketShakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfiguration
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 16:37
 */
@EnableWebSocket
@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 配置处理器
        registry.addHandler(this.webSocketHandler(), "/")
                // 配置拦截器
                .addInterceptors(new DemoWebSocketShakeInterceptor())
                // 解决跨域问题
                .setAllowedOrigins("*");
    }

    @Bean
    public DemoWebSocketHandler webSocketHandler() {
        return new DemoWebSocketHandler();
    }

    @Bean
    public DemoWebSocketShakeInterceptor webSocketShakeInterceptor() {
        return new DemoWebSocketShakeInterceptor();
    }
}
