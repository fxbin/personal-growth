package cn.fxbin.record.basic.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * AgencyInterceptor
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:47
 */
public class AgencyInterceptor implements MethodInterceptor {

    /**
     * intercept
     *
     * @author fxbin
     * @since 2020/10/26 10:48
     * @param o 动态生成的代理类对象
     * @param method 被代理对象的方法
     * @param objects 需要被执行的方法参数
     * @param methodProxy 生成代理类的方法
     * @return java.lang.Object
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        long startTime = System.currentTimeMillis();
        // 执行父类中的具体方法，即执行委托类中对应方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println(method.getName() + " cost time is: " + (System.currentTimeMillis() - startTime));
        return result;
    }
}
