package cn.fxbin.record.basic.juc.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceVsSync
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 11:50
 */
@Measurement(iterations = 20)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class AtomicReferenceVsSync {

    @State(Scope.Group)
    public static class MonitorRace {

        private DebitCard debitCard = new DebitCard("Alex", 0);

        public void syncInc() {
            synchronized (AtomicReferenceExample.class) {
                final DebitCard dc = debitCard;
                final DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                this.debitCard = newDC;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicReferenceRace {

        private AtomicReference<DebitCard> debitCardAtomicReference = new AtomicReference<>(new DebitCard("Alex", 0));

        public void casInc() {

            DebitCard debitCard = debitCardAtomicReference.get();
            DebitCard dc = new DebitCard(debitCard.getAccount(), debitCard.getAmount() + 10);
            debitCardAtomicReference.compareAndSet(debitCard, dc);
        }
    }

    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void syncInc(MonitorRace monitorRace) {
        monitorRace.syncInc();
    }

    @GroupThreads(10)
    @Group("cas")
    @Benchmark
    public void casInc(AtomicReferenceRace atomicReferenceRace) {
        atomicReferenceRace.casInc();
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(AtomicReferenceExample.class.getSimpleName())
                .forks(1)
                .timeout(TimeValue.seconds(10))
                .addProfiler(StackProfiler.class)
                .build();
        new Runner(opts).run();
    }


}
