package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMHExample07
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/13 14:45
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)// 设置5个线程运行基准测试方法
public class JMHExample07 {

    /**
     * Test 的实例将会被多个线程共享
     */
    @State(Scope.Benchmark)
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
                .include(JMHExample07.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
