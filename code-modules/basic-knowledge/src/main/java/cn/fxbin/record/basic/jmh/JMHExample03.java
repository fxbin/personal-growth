package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMHExample03
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/13 14:45
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)// 度量5个批次
@Warmup(iterations = 3)// 预热3个批次
public class JMHExample03 {

    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }


    @Measurement(iterations = 10)
    @Warmup(iterations = 5)
    @Benchmark
    public void test02() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample03.class.getSimpleName())
                .forks(1)
//                // 度量执行的批次为5， 也就是说在这5个批次中，对基准方法的执行与调用将会纳入统计
//                .measurementIterations(5)
//                // 在真正的度量之前，首先会对代码进行3个批次的热身，是代码运行达到JVM 已经优化的效果
//                .warmupIterations(3)
                .build();
        new Runner(options).run();
    }

}
