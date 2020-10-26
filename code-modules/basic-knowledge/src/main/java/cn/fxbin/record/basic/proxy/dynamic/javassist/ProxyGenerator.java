package cn.fxbin.record.basic.proxy.dynamic.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.reflect.InvocationHandler;

/**
 * ProxyGenerator
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 11:48
 */
public class ProxyGenerator {

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Throwable {
        ClassPool pool = ClassPool.getDefault();

        // ①创建代理类：public class BusinessProxy
        CtClass proxyCc = pool.makeClass("BusinessProxy");

        // ②给代理类添加字段：private InvocationHandler h;
        CtClass handlerCc = pool.get(InvocationHandler.class.getName());
        CtField handlerField = new CtField(handlerCc, "h", proxyCc);
        handlerField.setModifiers(AccessFlag.PRIVATE);
        proxyCc.addField(handlerField);

        // ③生成构造函数：public BusinessProxy(InvocationHandler h) { this.h = h; }
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{handlerCc}, proxyCc);
        // $0 代表 this, $1 代表构造函数的第 1 个参数
        ctConstructor.setBody("$0.h = $1;");
        proxyCc.addConstructor(ctConstructor);

        // ④依次为代理类实现相关接口
        for (Class<?> interfaceClass : interfaces) {
            // 为代理类添加相应接口方法及实现
            CtClass interfaceCc = pool.get(interfaceClass.getName());
            // 为代理类添加接口：public class BusinessProxy implements Operator
            proxyCc.addInterface(interfaceCc);
            // 为代理类添加相应方法及实现
            CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
            for (int i = 0; i < ctMethods.length; i++) {
                // 新的方法名，即需要被代理的方法
                String methodFieldName = "m" + i;
                // 为代理类添加反射方法字段
                // 如：private static Method m1 = Class.forName("com.limynl.Operator").getDeclaredMethod("sale", new Class[0]);
                // 构造反射字段声明及赋值语句
                // 方法的多个参数类型以英文逗号分隔
                String classParamsStr = "new Class[0]";
                // getParameterTypes 获取方法参数类型列表
                if (ctMethods[i].getParameterTypes().length > 0) {
                    for (CtClass clazz : ctMethods[i].getParameterTypes()) {
                        classParamsStr = (("new Class[0]".equals(classParamsStr)) ? clazz.getName() : classParamsStr + "," + clazz.getName()) + ".class";
                    }
                    classParamsStr = "new Class[] {" + classParamsStr + "}";
                }
                String methodFieldTpl = "private static java.lang.reflect.Method %s=Class.forName(\"%s\").getDeclaredMethod(\"%s\", %s);";
                String methodFieldBody = String.format(methodFieldTpl, "m" + i, interfaceClass.getName(), ctMethods[i].getName(), classParamsStr);
                // 为代理类添加反射方法字段. CtField.make(String sourceCodeText, CtClass addToThisClass)
                CtField methodField = CtField.make(methodFieldBody, proxyCc);
                proxyCc.addField(methodField);

                // 为方法添加方法体
                // 构造方法体. this.h.invoke(this, 反射字段名, 方法参数列表);
                String methodBody = "$0.h.invoke($0, " + methodFieldName + ", $args)";
                // 如果方法有返回类型，则需要转换为相应类型后返回，因为 invoke 方法的返回类型为 Object
                if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
                    // 对 8 个基本类型进行转型
                    // 例如：((Integer)this.h.invoke(this, this.m2, new Object[] { paramString, new Boolean(paramBoolean), paramObject })).intValue();
                    if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
                        CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
                        methodBody = "return ((" + ctPrimitiveType.getWrapperName() + ") " + methodBody + ")." + ctPrimitiveType.getGetMethodName() + "()";
                    } else {
                        // 对于非基本类型直接转型即可
                        methodBody = "return (" + ctMethods[i].getReturnType().getName() + ") " + methodBody;
                    }
                }
                methodBody += ";";
                // 为代理类添加方法. CtMethod(CtClass returnType, String methodName, CtClass[] parameterTypes, CtClass addToThisClass)
                CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
                        ctMethods[i].getParameterTypes(), proxyCc);
                newMethod.setBody(methodBody);
                proxyCc.addMethod(newMethod);
            }
        }
        // 将代理类字节码文件写到指定目录，方便我们查看源码
        proxyCc.writeFile("D:/");

        // ⑤生成代理实例. 将入参 InvocationHandler h 设置到代理类的 InvocationHandler h 变量
        return proxyCc.toClass().getConstructor(InvocationHandler.class).newInstance(h);
    }
}
