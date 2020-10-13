## JMH 的基本用法

### @Benchmark标记基准测试方法

> 与Junit4.x版本需要使用@Test注解标记单元测试方法一样，JMH对基准测试的方法需要使用@Benchmark注解进行标记，否则方法将被视为普通方法，并且不会对其执行基准测试。
> 如果一个类中没有任何基准测试方法（被@Benchmark标记的方法），那么对其进行基准测试则会出现异常。

### Warmup以及Measurement

* Warmup
> Warmup可直译为“预热”的意思，在JMH中，Warmup所做的就是在基准测试代码正式度量之前，先对其进行预热，使得代码的执行是经历过了类的早期优化、JVM运行期编译、JIT优化之后的最终状态，
 从而能够获得代码真实的性能数据。

* Measurement
> Measurement则是真正的度量操作，在每一轮的度量中，所有的度量数据会被纳入统计之中（预热数据不会纳入统计之中）。

* 使用方法

    * 设置全局的Warmup和Measurement
        * 构造Options时设置Warmup和Measurement的执行批次
            ```java_holder_method_tree
                public static void main(String[] args) throws RunnerException {
                        final Options options = new OptionsBuilder()
                                .include(JMHExample03.class.getSimpleName())
                                .forks(1)
                                // 度量执行的批次为5， 也就是说在这5个批次中，对基准方法的执行与调用将会纳入统计
                                .measurementIterations(5)
                                // 在真正的度量之前，首先会对代码进行3个批次的热身，是代码运行达到JVM 已经优化的效果
                                .warmupIterations(3)
                                .build();
                        new Runner(options).run();
                    }
            ```
        * 使用@Measurement和@Warmup注解进行设置
            ```java_holder_method_tree
                @BenchmarkMode(Mode.AverageTime)
                @OutputTimeUnit(TimeUnit.MICROSECONDS)
                @State(Scope.Thread)
                @Measurement(iterations = 5)// 度量5个批次
                @Warmup(iterations = 3)// 预热3个批次
                public class JMHExample03 {
                }
            ```
    * 在基准测试方法上设置Warmup和Measurement
        ```java_holder_method_tree
            @Measurement(iterations = 10)
            @Warmup(iterations = 5)
            @Benchmark
            public void test02() throws InterruptedException {
                TimeUnit.MILLISECONDS.sleep(10);
            }
        ```
    * Warmup和Measurement执行相关的输出
    
### 四大BenchmarkMode

> JMH使用@BenchmarkMode这个注解来声明使用哪一种模式来运行，JMH为我们提供了四种运行模式，当然它还允许若干个模式同时存在

* AverageTime
> AverageTime（平均响应时间），它主要用于输出基准测试方法每调用一次所耗费的时间，也就是elapsed time/operation。

* Throughput
> Throughput（方法吞吐量）则刚好与AverageTime相反，它的输出信息表明了在单位时间内可以对该方法调用多少次。

* SampleTime
> SampleTime（时间采样）的方式是指采用一种抽样的方式来统计基准测试方法的性能结果，与我们常见的Histogram图（直方图）几乎是一样的，它会收集所有的性能数据，并且将其分布在不同的区间中。

* SingleShotTime
> SingleShotTime主要可用来进行冷测试，不论是Warmup还是Measurement，在每一个批次中基准测试方法只会被执行一次，一般情况下，我们会将Warmup的批次设置为0。

* 多Mode以及All
> 我们除了对某个基准测试方法设置上述四个模式中的一个之外，还可以为其设置多个模式的方式运行基准测试方法，如果你愿意，甚至可以设置全部的Mode。

* [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample04.java)

### OutputTimeUnit

> OutputTimeUnit提供了统计结果输出时的单位，比如，调用一次该方法将会耗费多少个单位时间，或者在单位时间内对该方法进行了多少次的调用，
> 同样，OutputTimeUnit既可以设置在class上，也可以设置在method上，还可以在Options中进行设置，它们的覆盖次序与BenchmarkMode一致


### 三大State的使用

> 在JMH中，有三大State分别对应于Scope的三个枚举值。
> * Benchmark
> * Thread
> * Group

* Thread独享的State
> 所谓线程独享的State是指，每一个运行基准测试方法的线程都会持有一个独立的对象实例，该实例既可能是作为基准测试方法参数传入的，也可能是运行基准方法所在的宿主class，
> 将State设置为Scope.Thread一般主要是针对非线程安全的类。
>
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample06.java)

* Thread共享的State
> 在多线程的情况下某个类被不同线程操作时的性能，比如，多线程访问某个共享数据时，我们需要让多个线程使用同一个实例才可以
> JMH提供了多线程共享的一种状态Scope.Benchmark
>
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample07.java)

* 线程组共享的State
> 第一，是在多线程情况下的单个实例；第二，允许一个以上的基准测试方法并发并行地运行。
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample08.java)

### @Param

* 对比ConcurrentHashMap和SynchronizedMap的性能

> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample09.java)

* 使用@Param

> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample10.java)

### JMH的测试套件（Fixture）

* Setup以及TearDown

> @Setup会在每一个基准测试方法执行前被调用，通常用于资源的初始化
>
> @TearDown则会在基准测试方法被执行之后被调用，通常可用于资源的回收清理工作
>
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample11.java)

* Level
    * Trial：Setup和TearDown默认的配置，该套件方法会在每一个基准测试方法的所有批次执行的前后被执行。
        ```java_holder_method_tree
            @Setup(Level.Trial)
            public void setUp() {}
        ```
    * Iteration：由于我们可以设置Warmup和Measurement，因此每一个基准测试方法都会被执行若干个批次，如果想要在每一个基准测试批次执行的前后调用套件方法，则可以将Level设置为Iteration。
        ```java_holder_method_tree
            @Setup(Level.Iteration)
            public void setUp() {}
        ```
    * Invocation：将Level设置为Invocation意味着在每一个批次的度量过程中，每一次对基准方法的调用前后都会执行套件方法。
        ```java_holder_method_tree
            @Setup(Level.Invocation)
            public void setUp() {}
        ```

### CompilerControl

> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample12.java)


### 高级用法

* 避免DCE（Dead Code Elimination）
> 所谓Dead Code Elimination是指JVM为我们擦去了一些上下文无关，甚至经过计算之后确定压根不会用到的代码，比如下面这样的代码片段。
> ```java_holder_method_tree
>  public void test() {
>     int x = 10;
>     int y = 10;
>     int z = x + y;
>  }
> ```
> 我们在test方法中分别定义了x和y，并且经过相加运算得到了z，但是在该方法的下文中再也没有其他地方使用到z（既没有对z进行返回，也没有对其进行二次使用，z甚至不是一个全局的变量），
> JVM很有可能会将test()方法当作一个空的方法来看待，也就是说会擦除对x、y的定义，以及计算z的相关代码
>
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample13.java)

* 使用Blackhole

> JMH提供了一个称为Blackhole的类，可以在不作任何返回的情况下避免DeadCode的发生，Blackhole直译为“黑洞”，与Linux系统下的黑洞设备/dev/null非常相似
> 
> Blackhole可以帮助你在无返回值的基准测试方法中避免DC（DeadCode）情况的发生。
>
> [代码清单](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh/JMHExample14.java)

* 避免常量折叠（Constant Folding）
> 常量折叠是Java编译器早期的一种优化——编译优化。在javac对源文件进行编译的过程中，通过词法分析可以发现某些常量是可以被折叠的，也就是可以直接将计算结果存放到声明中，而不需要在执行阶段再次进行运算。
>
>```java_holder_method_tree
> private final int x = 10;
> private final int y = x*20;
>```
> 在编译阶段，y的值将被直接赋予200，这就是所谓的常量折叠
>
> 

      