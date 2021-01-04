package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicIntegerDemo, 演示 AtomicInteger 的基本用法，对比非原子类的线程安全问题
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/21 22:42
 */
public class AtomicIntegerDemo implements Runnable{


    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount = 0;

    public void incrementBasic() {
        basicCount ++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo r = new AtomicIntegerDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("原子类的结果" + atomicInteger.get());
        System.out.println("普通变量的结果" + basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }



}
