package me.www.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * <websocket配置类>
 * <功能详细描述>
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    // 注册消息处理器，并映射连接地址
    // ws://localhost:8080/websocket/task?userName=js&taskId=1
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册消息处理器，并添加自定义拦截器，支持websocket的连接访问
        registry.addHandler(new TaskWebSocketHandler(), "/websocket/task")
                .addInterceptors(new TaskHandshakeInterceptor())
                .setAllowedOrigins("*");

        /* 注册消息处理器，并添加自定义拦截器,添加不支持websocket的连接访问
        SockJs是一个WebSocket的通信js库，Spring对这个js库进行了后台的自动支持，
        也就是说，如果使用SockJs，那么我们就不需要对后台进行更多的配置，只需要加上withSockJS()这一句就可以了*/
        registry.addHandler(new TaskWebSocketHandler(), "/websocket/task/sockjs")
                .addInterceptors(new TaskHandshakeInterceptor())
                .setAllowedOrigins("*")
                .withSockJS();
    }

}
