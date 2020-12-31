package cn.fxbin.record.basic.juc.concurrent_util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CyclicBarrierExample
 *
 *
 * <p>
 *
 *     CyclicBarrier的另一个很好的特性是可以被循环使用，也就是说当其内部的计数器为0之后还可以在接下来的使用中重置而无须重新定义一个新的
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 15:16
 */
public class CyclicBarrierExample2 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier barrier = new CyclicBarrier(11);

        for (int i = 0; i < 10; i++) {
            new Thread(new Tourist(i, barrier)).start();
        }

        barrier.await();
        System.out.printf("Tour Guider: all of Tourist get on the bus");
        barrier.await();
        System.out.printf("Tour Guider: all of Tourist get off the bus");

    }

    private static class Tourist implements Runnable {

        private final int touristId;

        private final CyclicBarrier barrier;

        public Tourist(int touristId, CyclicBarrier barrier) {
            this.touristId = touristId;
            this.barrier = barrier;
        }

        @Override
        public void run() {

            System.out.printf("Tourist: %d by bus\n", touristId);
            // 模拟乘客上车的时间开销
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist: %d Get on the bus, and wait other people reached\n");

            System.out.printf("Tourist: %d arrival the destination\n", touristId);

            // 模拟乘客下车
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist: %d Get off the bus, and wait other people get off.\n");


        }

        private void waitAndPrint(String message) {
            System.out.printf(message, touristId);

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void spendSeveralSeconds() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
