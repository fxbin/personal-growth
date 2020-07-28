package cn.fxbin.record.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SendResponse
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:59
 */
@Data
@Accessors(chain = true)
public class SendResponse implements Message {

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

}
