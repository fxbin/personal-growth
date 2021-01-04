package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * LongAccumulatorDemo
 *
 * <p>
 *    演示 LongAccumulator 用法
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2021/1/4 23:17
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {

        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);

        ExecutorService executors = Executors.newFixedThreadPool(8);

        IntStream.range(1, 10).forEach(i -> executors.submit(() -> accumulator.accumulate(i)));
        executors.shutdown();
        while (!executors.isTerminated()){

        }
        System.out.println(accumulator.getThenReset());

    }

}
