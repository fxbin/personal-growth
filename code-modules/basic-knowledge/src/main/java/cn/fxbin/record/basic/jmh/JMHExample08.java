package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JMHExample08
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
public class JMHExample08 {

    /**
     * Test 设置为线程组共享
     */
    @State(Scope.Group)
    public static class Test{
        public Test() {
            System.out.println("create instance");
        }

        public void write() {
            System.out.println("write");
        }

        public void read() {
            System.out.println("read");
        }
    }

    /**
     * 在线程组 "test" 中，有三个线程将不断地对 Test 实例的 write 方法进行调用
     */
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testWrite(Test test) {
        test.write();
    }

    /**
     * 在线程组 "test" 中，有三个线程将不断地对 Test 实例的 read 方法进行调用
     */
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testRead(Test test) {
        test.read();
    }


    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample08.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
