package cn.fxbin.record.basic.juc.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FireLock
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/7 23:37
 */
public class FireLock {

    public static void main(String[] args) throws Exception {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始打印");
                printQueue.printJob(new Object());
                System.out.println(Thread.currentThread().getName() + "打印完毕");
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            Thread.sleep(1000);
        }

    }

    static class PrintQueue {
        private Lock queueLock = new ReentrantLock(true);

        public void printJob (Object document) {
            queueLock.lock();
            try {
                int duration = new Random().nextInt(10) + 1;
                System.out.println(Thread.currentThread().getName() + "正在打印，需要" + duration * 1000);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }

            queueLock.lock();
            try {
                int duration = new Random().nextInt(10) + 1;
                System.out.println(Thread.currentThread().getName() + "正在打印，需要" + duration * 1000);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }

}
