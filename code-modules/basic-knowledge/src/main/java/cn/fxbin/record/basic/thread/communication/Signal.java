package cn.fxbin.record.basic.thread.communication;

/**
 * 让线程A输出0，然后线程B输出1，再然后线程A输出2…以此类推
 * DEMO
 *
 *  volatile变量需要进行原子操作, 根据需要使用synchronized给它“上锁”，或者是使用AtomicInteger等原子类
 */
public class Signal {


    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("threadA: " + signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("threadB: " + signal);
                    signal = signal + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

}
