package cn.fxbin.record.basic.thread.basic;

/**
 * ThreadDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/1 16:18
 */
public class ThreadDemo {

    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static class MyThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        Thread myThread1 = new MyThread1();
        myThread1.start();

        new Thread(new MyThread2()).start();

        // Java 8 函数式编程
        new Thread(() -> System.out.println("Java8 匿名内部类")).start();
    }

}
