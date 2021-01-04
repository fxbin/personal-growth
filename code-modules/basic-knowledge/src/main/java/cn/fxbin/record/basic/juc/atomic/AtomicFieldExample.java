package cn.fxbin.record.basic.juc.atomic;

import javafx.scene.control.Alert;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicFieldExample
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 14:16
 */
public class AtomicFieldExample implements Runnable{

    public static void main(String[] args) throws InterruptedException {

        a = new Alex();
        b = new Alex();

        AtomicFieldExample example = new AtomicFieldExample();
        Runnable target;
        Thread t1 = new Thread(example);
        Thread t2 = new Thread(example);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量a:" + a);
        System.out.println("升级变量b:" + b);
    }

    static Alex a;

    static Alex b;

    public static AtomicIntegerFieldUpdater<Alex> updater =
            AtomicIntegerFieldUpdater.newUpdater(Alex.class, "salary");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a.salary++;
            updater.getAndIncrement(b);
        }
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
