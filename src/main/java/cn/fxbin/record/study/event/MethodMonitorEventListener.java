package cn.fxbin.record.study.event;

import java.util.EventListener;

/**
 * MethodMonitorEventListener
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 15:15
 */
public interface MethodMonitorEventListener extends EventListener {

    /**
     * onMethodBegin 处理方法执行之前发布的事件
     *
     * @since 2020/7/25 15:16
     * @param event cn.fxbin.record.study.event.MethodMonitorEvent
     */
    void onMethodBegin(MethodMonitorEvent event);


    /**
     * onMethodEnd 处理方法结束时发布的事件
     *
     * @since 2020/7/25 15:16
     * @param event cn.fxbin.record.study.event.MethodMonitorEvent
     */
    void onMethodEnd(MethodMonitorEvent event);

}
