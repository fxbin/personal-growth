package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JMHExample11
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
public class JMHExample11 {

    private List<String> list;

    @Setup
    public void setUp() {
        this.list = new ArrayList<>();
    }

    @Benchmark
    public void measureRight() {
        this.list.add("Test");
    }

    @Benchmark
    public void measureWrong() {
        // do nothing
    }

    /**
     * 将方法标记为 @TearDown, 运行资源回收甚至断言的操作
     */
    @TearDown
    public void tearDown() {
        assert this.list.size() > 0 : "The list elements must greater than zero";
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample11.class.getSimpleName())
                // 激活断言， enable assertion
                .jvmArgs("-ea")
                .build();
        new Runner(options).run();
    }

}
