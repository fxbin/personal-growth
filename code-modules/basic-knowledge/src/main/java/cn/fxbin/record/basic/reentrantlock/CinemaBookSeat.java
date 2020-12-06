package cn.fxbin.record.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * CinemaBookSeat
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/6 23:40
 */
public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始预订座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "完成预订座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
        new Thread(CinemaBookSeat::bookSeat).start();
    }
}
