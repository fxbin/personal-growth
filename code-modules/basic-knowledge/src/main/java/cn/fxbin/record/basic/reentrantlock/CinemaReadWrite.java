package cn.fxbin.record.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CinemaBookSeat
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/6 23:40
 */
public class CinemaReadWrite {

    private static ReentrantReadWriteLock.ReadLock readLock = new ReentrantReadWriteLock().readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "取到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "取到了写，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(CinemaReadWrite::read).start();
        new Thread(CinemaReadWrite::read).start();
        new Thread(CinemaReadWrite::write).start();
        new Thread(CinemaReadWrite::write).start();
    }
}
