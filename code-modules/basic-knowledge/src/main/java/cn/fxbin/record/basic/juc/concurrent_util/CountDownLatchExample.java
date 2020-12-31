package cn.fxbin.record.basic.juc.concurrent_util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CountDownLatchExample
 *
 * <p>
 *     CountDownLatch, 优雅地解决主任务等待所有子任务都执行结束之后再进行下一步工作的场景
 * </p>
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/12/31 14:48
 */
public class CountDownLatchExample {

    /**
     * 模拟。。。
     */
    public static void main(String[] args) throws InterruptedException {

        // 获取商品编号列表
        int[] products = getProductsByCategoryId();

        // 通过 stream 的map 运算将商品编号转化为 productPrice
        List<ProductPrice> list = Arrays.stream(products)
                .mapToObj(ProductPrice::new)
                .collect(Collectors.toList());

        // 定义 CountDownLatch 计数器数量为子任务的个数
        final CountDownLatch latch = new CountDownLatch(products.length);
        list.forEach(pp -> {
            new Thread(() -> {
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
                    e.printStackTrace();
                } finally {
                    // 计数器 count down. 子任务执行完成
                    latch.countDown();
                }
            }).start();
        });

        // 主线程阻塞等待所有子任务结束，如果有一个子任务没有完成，则会一直等待
        latch.await();
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
