package cn.fxbin.record.basic.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TryLockExample
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/30 17:54
 */
public class TryLockExample {

    private final static Object obj = new Object();

    public static void main(String[] args) {
        final TryLock lock = new TryLock();
        final List<Object> validation = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        if (lock.tryLock()) {
                            System.out.println(Thread.currentThread() + ", 获取到了锁");
                            if (validation.size() > 1) {
                                throw new IllegalStateException("validation failed");
                            }
                            validation.add(obj);
                            TimeUnit.MILLISECONDS.sleep(1000L);
                        } else {
                            System.out.println("未获取到锁，休眠。。。");
                            TimeUnit.MILLISECONDS.sleep(1000L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.release()) {
                            System.out.println(Thread.currentThread() + "释放锁成功");
                            validation.remove(obj);
                        }
                    }
                }
            }).start();
        }
    }

}
