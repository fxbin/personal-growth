package cn.fxbin.record.websocket.handler;

import cn.fxbin.record.websocket.message.SendResponse;
import cn.fxbin.record.websocket.message.SendToAllRequest;
import cn.fxbin.record.websocket.message.SendToUserRequest;
import cn.fxbin.record.websocket.util.WebSocketUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;


/**
 * SendToAllHandler
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 16:10
 */
@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {

    @Override
    public void execute(WebSocketSession session, SendToAllRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtils.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtils.broadcast(SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
