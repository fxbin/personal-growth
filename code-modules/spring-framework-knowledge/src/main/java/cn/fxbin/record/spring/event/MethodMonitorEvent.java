package cn.fxbin.record.spring.event;

import java.util.EventObject;

/**
 * MethodMonitorEvent
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 15:15
 */
public class MethodMonitorEvent extends EventObject {

    // 时间戳，用于记录方法开始执行时间
    public long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
