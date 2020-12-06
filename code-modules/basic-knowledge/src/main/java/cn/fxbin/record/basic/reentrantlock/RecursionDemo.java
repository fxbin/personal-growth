package cn.fxbin.record.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * RecursionDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/7 0:05
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }

}
