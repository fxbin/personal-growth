package cn.fxbin.record.study.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * AuthResponse
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:53
 */
@Data
@Accessors(chain = true)
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

}
