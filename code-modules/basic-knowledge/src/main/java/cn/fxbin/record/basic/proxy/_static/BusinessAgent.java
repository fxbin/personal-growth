package cn.fxbin.record.basic.proxy._static;

import cn.fxbin.record.basic.proxy.Factory;
import cn.fxbin.record.basic.proxy.Operator;

/**
 * BusinessAgent
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:29
 */
public class BusinessAgent implements Operator {

    private Factory factory;

    public BusinessAgent(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void sale() {
        factory.sale();
    }

    @Override
    public void expand() {
        factory.expand();
    }
}
