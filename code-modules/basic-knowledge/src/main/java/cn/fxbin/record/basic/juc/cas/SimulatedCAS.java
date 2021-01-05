package cn.fxbin.record.basic.juc.cas;

/**
 * SimulatedCAS
 *
 * <p>
 *     模拟 CAS 操作，等价代码
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2021/1/4 23:44
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
