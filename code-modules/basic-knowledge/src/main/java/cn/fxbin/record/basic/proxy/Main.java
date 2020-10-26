package cn.fxbin.record.basic.proxy;

import cn.fxbin.record.basic.proxy.dynamic.cglib.AgencyInterceptor;
import cn.fxbin.record.basic.proxy.dynamic.javassist.ProxyGenerator;
import cn.fxbin.record.basic.proxy.dynamic.jdk.AgencyHandler;
import javassist.*;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Main
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:38
 */
public class Main {

    public static void main(String[] args) throws Throwable {

        // JDK 动态代理
        AgencyHandler agencyHandler = new AgencyHandler(new Factory());
        // 创建代理对象
        Operator operator = (Operator) Proxy.newProxyInstance(
                Operator.class.getClassLoader(),
                new Class[]{Operator.class},
                agencyHandler);
        operator.sale();
        operator.expand();



        // CGLib 动态代理
        // 使用 enhancer 创建动态代理对象
        Enhancer enhancer = new Enhancer();
        // 指定需要代理的委托类
        enhancer.setSuperclass(Factory.class);
        // 设置回调，对于代理类上所有方法的调用，都会执行 AgencyInterceptor 中的 intercept 对其拦截
        enhancer.setCallback(new AgencyInterceptor());
        // 获得创建的代理对象
        Factory factoryProxy = (Factory) enhancer.create();
        // 使用代理对象进行代理访问
        factoryProxy.sale();
        factoryProxy.expand();


        // javassist 动态代理
        Operator javassistProxyOperator = (Operator) ProxyGenerator
                .newProxyInstance(Operator.class.getClassLoader(),
                        new Class[]{Operator.class}, agencyHandler);
        operator.sale();




    }

}
