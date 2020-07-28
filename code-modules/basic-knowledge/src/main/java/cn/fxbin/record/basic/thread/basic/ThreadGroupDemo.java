package cn.fxbin.record.basic.thread.basic;

/**
 * ThreadGroupDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/6/29 10:00
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread 当前线程组名字：" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("thread 当前线程名字:" + Thread.currentThread().getName());
        });

        thread.start();
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());


        // 线程组的常用方法

        // 获取当前的线程组名字
        Thread.currentThread().getThreadGroup().getName();

        //复制线程组
        // 复制一个线程数组到一个线程组

        ThreadGroup oneThreadGroup = new ThreadGroup("oneThreadGroup");
        Thread[] threads = new Thread[oneThreadGroup.activeCount()];
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        threadGroup.enumerate(threads);

        // 线程组统一异常处理
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下方法
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };

        // 这个线程是threadGroup1的一员
        Thread thread1 = new Thread(threadGroup1, () -> {
            // 抛出unchecked异常
            throw new RuntimeException("测试异常");
        });

        thread1.start();

    }
}
