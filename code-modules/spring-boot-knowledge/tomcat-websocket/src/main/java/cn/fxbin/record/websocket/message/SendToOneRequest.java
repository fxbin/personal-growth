package cn.fxbin.record.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SendToOneRequest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:56
 */
@Data
@Accessors(chain = true)
public class SendToOneRequest implements Message {

    public static final String TYPE = "SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}
