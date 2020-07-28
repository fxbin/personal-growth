package cn.fxbin.record.websocket.handler;


import cn.fxbin.record.websocket.message.Message;

import javax.websocket.Session;

/**
 * MessageHandler
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:58
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param session 会话
     * @param message 消息
     */
    void execute(Session session, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

}
