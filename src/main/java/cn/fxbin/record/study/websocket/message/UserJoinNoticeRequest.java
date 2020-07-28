package cn.fxbin.record.study.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserJoinNoticeRequest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 15:54
 */
@Data
@Accessors(chain = true)
public class UserJoinNoticeRequest implements Message {

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;


}
