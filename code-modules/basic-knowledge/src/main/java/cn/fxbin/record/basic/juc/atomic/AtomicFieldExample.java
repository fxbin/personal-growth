package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicFieldExample
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 14:16
 */
public class AtomicFieldExample {

    public static void main(String[] args) {

        AtomicIntegerFieldUpdater<Alex> updater =
                AtomicIntegerFieldUpdater.newUpdater(Alex.class, "salary");

        Alex alex = new Alex();
        int result = updater.addAndGet(alex, 1);
        assert result == 1;
        System.out.println(alex);
    }

    public static class Alex {

        volatile int salary;

        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Alex{" +
                    "salary=" + salary +
                    '}';
        }
    }

}
