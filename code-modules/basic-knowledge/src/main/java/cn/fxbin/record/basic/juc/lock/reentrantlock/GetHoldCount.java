package cn.fxbin.record.basic.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * GetHoldCount
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/7 0:03
 */
public class GetHoldCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

}
