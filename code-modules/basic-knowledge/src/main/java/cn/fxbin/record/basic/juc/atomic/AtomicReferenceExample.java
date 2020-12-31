package cn.fxbin.record.basic.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceExample
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 11:42
 */
public class AtomicReferenceExample {


    private static AtomicReference<DebitCard> debitCardAtomicReference = new AtomicReference<>(new DebitCard("Alex", 0));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {

                @Override
                public void run() {

                    while (true) {
                        // 获取 AtomicReference 当前值
                        final DebitCard dc = debitCardAtomicReference.get();
                        // 基于 AtomicReference 当前值创建一个新的 DebitCard
                        DebitCard debitCard = new DebitCard(dc.getAccount(), dc.getAmount() + 10);

                        // 基于 CAS 算法更新 AtomicReference 当前值
                        if(debitCardAtomicReference.compareAndSet(dc, debitCard)) {
                            System.out.println(debitCard);
                        }

                        try {
                            TimeUnit.MILLISECONDS.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }.start();
        }
    }


}
