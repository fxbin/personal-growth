package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.PI;

/**
 * JMHExample14
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/13 14:45
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JMHExample14 {


    double x1 = PI;
    double x2 = PI * 2;

    /**
     * 基准数据
     */
    @Benchmark
    public double baseline() {
        // 不是 dead code, 对结果进行的返回
        return Math.pow(x1, 2);
    }

    @Benchmark
    public double powBugReturnOne() {
        // Dead Code 会被擦除
        Math.pow(x1, 2);
        // 不会被擦除，返回了结果
        return Math.pow(x2, 2);
    }

    @Benchmark
    public double powThenAdd() {
        return Math.pow(x1, 2) + Math.pow(x2, 2);
    }

    @Benchmark
    public void useBlackhole(Blackhole blackhole) {
        blackhole.consume(Math.pow(x1, 2));
        blackhole.consume(Math.pow(x2, 2));
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample14.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
