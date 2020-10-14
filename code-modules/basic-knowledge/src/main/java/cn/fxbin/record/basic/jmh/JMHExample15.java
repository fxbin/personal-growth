package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.log;

/**
 * JMHExample15
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/14 10:10
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JMHExample15 {

    private final double x1 = 123.456;
    private final double x2 = 345.678;

    private double y1 = 123.456;
    private double y2 = 345.678;

    /**
     * 基准线, 返回 123.456 * 345.678 结果
     */
    @Benchmark
    public double baseline() {
        return 42676.023168d;
    }

    @Benchmark
    public double test1() {
        return x1 * x2;
    }

    @Benchmark
    public double test2() {
        return log(y1) * log(y2);
    }

    @Benchmark
    public double test3() {
        return log(x1) * log(x2);
    }


    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample15.class.getSimpleName())
                .build();
        new Runner(options).run();
    }



}
