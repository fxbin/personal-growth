package cn.fxbin.record.basic.thread.basic;

import java.util.concurrent.*;

/**
 * Task
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/6/29 9:41
 */
public class TaskDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟计算 1s
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        TaskDemo task = new TaskDemo();
        // future demo
        Future<Integer> result = executor.submit(task);
        // future task demo
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        System.out.println(result.get());
        System.out.println(futureTask.get());
    }
}
