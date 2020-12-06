package cn.fxbin.record.basic.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/6 23:44
 */
public class LockDemo {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        final Outputer outputer = new Outputer();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("大师兄");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("二师兄");
            }
        }).start();

    }

    static class Outputer {

        Lock lock = new ReentrantLock();

        public void output(String name) {

            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }

        }
    }
}
