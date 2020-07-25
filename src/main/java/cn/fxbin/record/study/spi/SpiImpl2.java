package cn.fxbin.record.study.spi;

/**
 * SpiImpl2
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/23 10:09
 */
public class SpiImpl2 implements SPIService {
    @Override
    public void execute() {
        System.out.println("SpiImpl2.execute");
    }
}
