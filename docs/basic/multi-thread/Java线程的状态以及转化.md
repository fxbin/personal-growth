> 在现在的操作系统中，线程是被视为轻量级进程的，所以操作系统线程的状态其实和操作系统进程的状态是一致的。

![Java线程的状态转换图](./images/系统进程状态转换图.png)

主要的三个状态：

* 就绪状态(ready)：线程正在等待使用CPU，经调度程序调用之后可进入running状态。
* 执行状态(running)：线程正在使用CPU。
* 等待状态(waiting): 线程经过等待事件的调用或者正在等待其他资源（如I/O）。

Java 线程的6个状态

```java
// Thread.State 源码
public enum State {
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED;
}
```

NEW

> new 状态的线程，是指还没调用Thread实例的start()方法

```java_holder_method_tree
private void testStateNew() {
    Thread thread = new Thread(() -> {});
    System.out.println(thread.getState()); // 输出 NEW 
}
```

RUNNABLE

> 表示当前线程正在运行中。处于RUNNABLE状态的线程在Java虚拟机中运行，也有可能在等待其他系统资源（比如I/O）。
> Java线程的RUNNABLE状态其实是包括了传统操作系统线程的ready和running两个状态的。

BLOCKED

> 阻塞状态。处于BLOCKED状态的线程正等待锁的释放以进入同步区。

WAITING

等待状态。处于等待状态的线程变成RUNNABLE状态需要其他线程唤醒。

调用如下3个方法会使线程进入等待状态：

* Object.wait()：使当前线程处于等待状态直到另一个线程唤醒它；
* Thread.join()：等待线程执行完毕，底层调用的是Object实例的wait方法；
* LockSupport.park()：除非获得调用许可，否则禁用当前线程进行线程调度。

TIME_WAITING

超时等待状态。线程等待一个具体的时间，时间到后会被自动唤醒。

调用如下方法会使线程进入超时等待状态：

* Thread.sleep(long millis)：使当前线程睡眠指定时间；
* Object.wait(long timeout)：线程休眠指定时间，等待期间可以通过notify()/notifyAll()唤醒；
* Thread.join(long millis)：等待当前线程最多执行millis毫秒，如果millis为0，则会一直执行；
* LockSupport.parkNanos(long nanos)： 除非获得调用许可，否则禁用当前线程进行线程调度指定时间；
* LockSupport.parkUntil(long deadline)：同上，也是禁止线程进行调度指定时间；

TERMINATED