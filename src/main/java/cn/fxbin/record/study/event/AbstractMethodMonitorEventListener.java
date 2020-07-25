package cn.fxbin.record.study.event;

/**
 * AbstractMethodMonitorEventListener
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 15:17
 */
public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {
    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        // 记录方法开始执行时的时间
        event.timestamp = System.currentTimeMillis();
    }

    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        // 计算方法耗时
        long duration = System.currentTimeMillis() - event.timestamp;
        System.out.println("耗时：" + duration);
    }
}
