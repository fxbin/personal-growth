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
 * JMHExample12
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
public class JMHExample12 {

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test1() {

    }

    /**
     * 禁止优化
     */
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test2() {
        log(PI);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample12.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
