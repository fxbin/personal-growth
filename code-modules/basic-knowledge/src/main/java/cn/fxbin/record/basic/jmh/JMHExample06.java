package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMHExample06
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/13 14:45
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)// 设置5个线程运行基准测试方法
public class JMHExample06 {

    /**
     * 5 个运行线程，每个线程都会持有一个Test 实例
     */
    @State(Scope.Thread)
    public static class Test{
        public Test() {
            System.out.println("create instance");
        }
        public void method() {

        }
    }

    @Benchmark
    public void test(Test test) {
        test.method();
    }


    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample06.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
