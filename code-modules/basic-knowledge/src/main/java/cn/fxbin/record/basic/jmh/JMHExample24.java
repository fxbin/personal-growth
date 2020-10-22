package cn.fxbin.record.basic.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.CompilerProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * JMHExample23
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/20 17:17
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample24 {

    private byte[] alexBytes;

    private AlexClassLoader classLoader;

    @Setup
    public void init() throws IOException {
        this.alexBytes = Files.readAllBytes(
                Paths.get("D:\\Project\\Github\\myself-wiki\\code-modules\\basic-knowledge\\target\\classes\\Alex.class")
        );
        this.classLoader = new AlexClassLoader(alexBytes);
    }

    @Benchmark
    public Object testLoadClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> alexClass = Class.forName("Alex", true, classLoader);
        return alexClass.newInstance();
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample24.class.getSimpleName())
                .addProfiler(CompilerProfiler.class)
                .verbosity(VerboseMode.EXTRA)
                .build();
        new Runner(options).run();
    }


}
