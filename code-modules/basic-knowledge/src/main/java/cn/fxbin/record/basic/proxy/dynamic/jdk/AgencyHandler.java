package cn.fxbin.record.basic.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * AgencyHandler
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:33
 */
public class AgencyHandler implements InvocationHandler {

    private Object target;

    public AgencyHandler() {
    }

    public AgencyHandler(Object target) {
        this.target = target;
    }

    /**
     * invoke
     *
     * @author fxbin
     * @since 2020/10/26 10:34
     * @param proxy 表示程序运行期间生成的代理类对象
     * @param method 表示代理对象被调用的方法
     * @param args 表示代理对象被调用的方法的参数
     * @return java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 使用烦着执行委托类对象具体方法
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " cost time is: " + (System.currentTimeMillis() - startTime));
        return result;
    }
}
