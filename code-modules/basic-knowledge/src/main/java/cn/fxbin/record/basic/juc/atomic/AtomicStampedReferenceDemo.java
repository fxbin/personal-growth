package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReferenceDemo
 *
 * <p>
 *     CAS算法的ABA问题，针对乐观锁再并发情况下的操作，通常增加版本号，如数据库乐观锁的实现，以此解决并发操作带来的 ABA 问题
 *
 *      AtomicStampedReference 在构建时， 需要一个类似于版本号的 int 类型变量 stamped, 每一次针对共享数据的变化都会导致该stamped的增加（stamped的自增维护需要应用程序自身去负责，AtomicStampedReference并不提供, 避免ABA 问题的出现
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2021/1/5 10:27
 */
public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("Hello", 1);

        // 设置成功
        reference.attemptStamp("Hello", 1);
        System.out.println(" value: " + reference.getReference() + ", tamp: " + reference.getStamp());

        // 设置失败，期望的引用值不等于当前引用值
        reference.attemptStamp("World", 2);
        System.out.println(" value: " + reference.getReference() + ", tamp: " + reference.getStamp());

        // 成功
        reference.attemptStamp("Hello", 2);
        System.out.println(" value: " + reference.getReference() + ", tamp: " + reference.getStamp());
    }


}
