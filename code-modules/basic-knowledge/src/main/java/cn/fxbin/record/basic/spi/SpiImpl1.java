package cn.fxbin.record.basic.spi;

/**
 * SpiImpl1
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/23 10:08
 */
public class SpiImpl1 implements SPIService {
    @Override
    public void execute() {
        System.out.println("SpiImpl1.execute");
    }
}
