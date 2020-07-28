package cn.fxbin.record.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SendToUserRequest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:59
 */
@Data
@Accessors(chain = true)
public class SendToUserRequest implements Message {

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}
