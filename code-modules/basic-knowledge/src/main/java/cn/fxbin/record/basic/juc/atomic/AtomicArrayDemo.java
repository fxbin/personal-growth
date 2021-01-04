package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicArrayDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/21 22:48
 */
public class AtomicArrayDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] threadIncrementer = new Thread[100];
        Thread[] threadDecrementer = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadIncrementer[i] = new Thread(incrementer);
            threadDecrementer[i] = new Thread(decrementer);

            threadIncrementer[i].start();
            threadDecrementer[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threadIncrementer[i].join();
            threadDecrementer[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("非0值" + i);
            }
        }
        System.out.println("结束");

    }
}

class Decrementer implements Runnable {

    private AtomicIntegerArray aia;

    public Decrementer(AtomicIntegerArray aia) {
        this.aia = aia;
    }

    @Override
    public void run() {
        for (int i = 0; i < aia.length(); i++) {
            aia.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable {
    private AtomicIntegerArray aia;

    public Incrementer(AtomicIntegerArray aia) {
        this.aia = aia;
    }
    @Override
    public void run() {
        for (int i = 0; i < aia.length(); i++) {
            aia.getAndIncrement(i);
        }
    }
}
