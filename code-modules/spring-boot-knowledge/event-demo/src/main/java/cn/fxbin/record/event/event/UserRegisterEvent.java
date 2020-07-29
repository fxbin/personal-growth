package cn.fxbin.record.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * UserRegisterEvent
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 17:09
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
