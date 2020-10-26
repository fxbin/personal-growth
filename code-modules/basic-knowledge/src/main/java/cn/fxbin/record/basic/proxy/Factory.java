package cn.fxbin.record.basic.proxy;

/**
 * Factory
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:29
 */
public class Factory implements Operator {

    @Override
    public void sale() {
        System.out.println("sale......");
    }

    @Override
    public void expand() {
        System.out.println("expand......");
    }
}
