package cn.fxbin.record.study.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SpiMain
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/23 10:11
 */
public class SpiMain {

    public static void main(String[] args) {
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

        while (providers.hasNext()) {
            SPIService spiService = providers.next();
            spiService.execute();
        }

        System.out.println("--------------------------------");

        Iterator<SPIService> iterator = load.iterator();
        while(iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }
    }

}
