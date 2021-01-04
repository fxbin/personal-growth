package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicLongDemo
 *
 * <p>
 *     演示高并发场景下， LongAdder 比 AtomicLong 性能好
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2021/1/4 22:52
 */
public class AtomicLongDemo {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()) {

        }
        System.out.println("AtomicLong 耗时：" + (System.currentTimeMillis() - l));

        long l1 = System.currentTimeMillis();
        LongAdder counter1 = new LongAdder();
        ExecutorService service1 = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10000; i++) {
            service1.submit(new Task1(counter1));
        }
        service1.shutdown();
        while (!service1.isTerminated()) {

        }
        System.out.println("LongAdder 耗时：" + (System.currentTimeMillis() - l1));
    }

    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }

    private static class Task1 implements Runnable {

        private LongAdder counter;

        public Task1(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }

}
