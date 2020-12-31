package cn.fxbin.record.basic.juc.concurrent_util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CyclicBarrierExample
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 15:16
 */
public class CyclicBarrierExample {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // 获取商品编号列表
        int[] products = getProductsByCategoryId();

        // 通过 stream 的map 运算将商品编号转化为 productPrice
        List<ProductPrice> list = Arrays.stream(products)
                .mapToObj(ProductPrice::new)
                .collect(Collectors.toList());

        // 定义 CyclicBarrier ,指定 prices 为子任务数量
//        final CyclicBarrier barrier = new CyclicBarrier(list.size());
        final CyclicBarrier barrier = new CyclicBarrier(list.size() + 1);
        // 用于存放线程任务的list
        final List<Thread> threadList = new ArrayList<>();
        list.forEach(pp -> {
            Thread thread = new Thread(() -> {
                System.out.println(pp.getProdId() + " start calculate price.");
                try {
                    // 模拟系统调用
                    TimeUnit.SECONDS.sleep(10);

                    if (pp.prodId % 2 == 0) {
                        pp.setPrice(pp.prodId * 0.9d);
                    } else {
                        pp.setPrice(pp.prodId * 0.71d);
                    }

                    System.out.println(pp.getProdId() + " price calculate completed.");

                } catch (InterruptedException e) {
                    // ignore
                } finally {
                    // 等待其它子线程到达 barrier point
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

            threadList.add(thread);
            thread.start();
        });

        // 等待所有子任务线程执行结束
//        threadList.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        barrier.await();
        System.out.println("all of prices calculate finished");
        list.forEach(System.out::println);
    }

    private static int[] getProductsByCategoryId() {
        return IntStream.rangeClosed(1, 10).toArray();
    }

    private static class ProductPrice {
        private final int prodId;

        private double price;

        public ProductPrice(int prodId) {
            this(prodId, -1);
        }

        public ProductPrice(int prodId, double price) {
            this.prodId = prodId;
            this.price = price;
        }

        public int getProdId() {
            return prodId;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ProductPrice{" +
                    "prodId=" + prodId +
                    ", price=" + price +
                    '}';
        }
    }

}
