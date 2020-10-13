package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.PI;
import static java.lang.Math.log;

/**
 * JMHExample13
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/13 14:45
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample13 {

    /**
     * 基准数据
     */
    @Benchmark
    public void baseline() {

    }

    @Benchmark
    public void measureLog1() {
        Math.log(PI);
    }

    @Benchmark
    public void measureLog2() {
        double result = Math.log(PI);
        // 对result进行数学运算，但是结果既不保存也不返回，更不会进行二级运算
        Math.log(result);
    }

    @Benchmark
    public double measureLog3() {
        return Math.log(PI);
    }


    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample13.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
