## JMH 简介

> JMH 是 Java Micro Benchmark Harness 的简写，是专门用于代码为基准测试的工具集（toolkit）。主要是基于方法层面的基准测试，精度可以达到纳秒级。
> 该工具是由 Oracle 内部实现 JIT 的大牛们编写的，他们应该比任何人都了解 JIT 以及 JVM 对于基准测试的影响。
>
> 由于现代JVM已经变得越来越智能，在Java文件的编译阶段、类的加载阶段，以及运行阶段都可能进行了不同程度的优化，因此开发者编写的代码在运行中未必会像自己所预期的那样具有相同的性能体现，
> JVM的开发者为了让普通开发者能够了解自己所编写的代码运行的情况，JMH便因此而生。

* [JMH 官方地址](http://openjdk.java.net/projects/code-tools/jmh/)


## JMH 比较典型的应用场景

* 想准确地知道某个方法需要执行多长时间，以及执行时间和输入之间的相关性
* 对比接口不同实现在给定条件下的吞吐量
* 查看多少百分比的请求在多长时间内完成

## JMH 使用

### 加入依赖

因为 JMH 是 JDK9 自带的，如果是 JDK9 之前的版本需要加入如下依赖（目前 JMH 的最新版本为 1.26）：

```
    <!-- https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-core -->
    <dependency>
        <groupId>org.openjdk.jmh</groupId>
        <artifactId>jmh-core</artifactId>
        <version>1.26</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-generator-annprocess -->
    <dependency>
        <groupId>org.openjdk.jmh</groupId>
        <artifactId>jmh-generator-annprocess</artifactId>
        <version>1.26</version>
        <scope>provided</scope>
    </dependency>
```

### 编写代码

[代码示例](https://github.com/fxbin/myself-wiki/tree/master/code-modules/basic-knowledge/src/main/java/cn/fxbin/record/basic/jmh)