package cn.fxbin.record.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SendToAllRequest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:57
 */
@Data
@Accessors(chain = true)
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}
