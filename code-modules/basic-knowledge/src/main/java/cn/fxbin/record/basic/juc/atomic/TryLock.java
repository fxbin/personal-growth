package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TryLock
 *
 * 借助 AtomicBoolean 实现一个可立即返回并且退出阻塞的显式锁 Lock
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/30 17:47
 */
public class TryLock {

    /**
     * 借助 AtomicBoolean 的布尔原子性操作方法
     */
    private final AtomicBoolean ab = new AtomicBoolean(false);

    /**
     * 用于存放与线程上下文关联的数据副本
     */
    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    public boolean tryLock() {
        //
        boolean result = ab.compareAndSet(false, true);
        if (result) {
            // 修改成功，同步更新 threadlocal 的数据副本值
            threadLocal.set(true);
        }
        return result;
    }

    public boolean release() {
        // 判断调用 release 方法的线程是否成功获得了该锁
        if (threadLocal.get()) {
            // 标记锁被释放，并且原子性的修改布尔值为 false
            threadLocal.set(false);
            return ab.compareAndSet(true, false);
        } else {
            return false;
        }
    }

}
