package cn.fxbin.record.websocket.handler;

import cn.fxbin.record.websocket.message.SendResponse;
import cn.fxbin.record.websocket.message.SendToOneRequest;
import cn.fxbin.record.websocket.message.SendToUserRequest;
import cn.fxbin.record.websocket.util.WebSocketUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * SendToOneHandler
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 16:09
 */
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(WebSocketSession session, SendToOneRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtils.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtils.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}