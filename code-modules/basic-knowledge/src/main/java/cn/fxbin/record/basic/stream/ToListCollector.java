package cn.fxbin.record.basic.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * ToListCollector
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/22 14:51
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 创建集合操作的起始点
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }


    /**
     * 累积遍历过的项目，原位修改累加器
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 恒等函数
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 修改第一个累加器，将其与第二个累加器的内容合并
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        };
    }


    /**
     * 为收集器添加 IDENTITY_FINISH 和 CONCURRENT 标志
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
