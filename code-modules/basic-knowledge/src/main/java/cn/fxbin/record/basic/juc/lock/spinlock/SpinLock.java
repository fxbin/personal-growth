package cn.fxbin.record.basic.juc.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * SpinLock 自旋锁
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/15 23:02
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
            System.out.println("自旋锁获取失败，再次尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("开始尝试获取自旋锁" + Thread.currentThread().getName());
                spinLock.lock();
                System.out.println("取到了自旋锁" + Thread.currentThread().getName());

                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println("释放了自旋锁" + Thread.currentThread().getName());
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

    }

}
