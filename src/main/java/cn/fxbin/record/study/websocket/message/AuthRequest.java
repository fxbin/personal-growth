package cn.fxbin.record.study.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * AuthRequest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:51
 */
@Data
@Accessors(chain = true)
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;

}
