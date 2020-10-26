package cn.fxbin.record.basic.proxy.dynamic.javassist;

import javassist.*;

import java.lang.reflect.Method;

/**
 * PersonMain
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 11:52
 */
public class PersonMain {

    public static void main(String[] args) throws Exception {

        // javassist
        // 程序运行时创建新的类
        String className = Person.class.getSimpleName();

        ClassPool classPool = ClassPool.getDefault();
        // 定义一个名为 Person 的新类
        CtClass ctClass = classPool.makeClass(className);

        // 定义成员变量name, 类型为 String
        CtField ctField = new CtField(classPool.get("java.lang.String"), "name", ctClass);
        // 设置成员变量 name 访问修饰符
        ctField.setModifiers(Modifier.PRIVATE);
        // 添加为类 Person 的成员变量
        ctClass.addField(ctField);

        // 定义构造函数
        CtClass[] parameters = new CtClass[]{classPool.get("java.lang.String")};
        CtConstructor constructor = new CtConstructor(parameters, ctClass);
        // 方法体 $0 表示 this，$1 表示方法的第一个参数
        String body = "{$0.name = $1;}";
        constructor.setBody(body);
        ctClass.addConstructor(constructor);

        // 定义 setName getName 方法
        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));

        // 定义 toString 方法
        CtClass returnType = classPool.get("java.lang.String");
        CtMethod toStringMethod = new CtMethod(returnType, "toString", null, ctClass);
        toStringMethod.setModifiers(Modifier.PUBLIC);
        toStringMethod.setBody("{return \"name=\"+$0.name;}");
        ctClass.addMethod(toStringMethod);

        // 生成 Class 对象
        Class<?> c = ctClass.toClass();
        Object person = c.getConstructor(String.class)
                .newInstance("fxbin");

        // 使用反射调用
        Method method = person.getClass().getMethod("toString", null);
        String result = (String) method.invoke(person, null);
        System.out.println(result);

//        // javassist
//        // 动态扩展已有类或接口， 思路： 将原方法命名为 toString\$1, 然后创建一个新方法名为 toString， 在新方法中调用原方法 toString\$1
//        String methodName = "toString";
//        // 定义一个名为 Person 的新类
//        CtClass clazz = ClassPool.getDefault().get("cn.fxbin.record.basic.proxy.dynamic.javassist.Person");
//        CtMethod ctMethod = clazz.getDeclaredMethod(methodName);
//        String newMethodName = methodName + "$1";
//        ctMethod.setName(newMethodName);
//
//        //使用原始方法名，定义一个新方法，在这个方法内部调用 loop$impl
//        CtMethod newMethod = CtNewMethod.make("public void " + methodName + "(){" +
//                        "long startTime=System.currentTimeMillis();" +
//                        "" + newMethodName + "();" +//调用 toString$1
//                        "System.out.println(\"耗时:\"+(System.currentTimeMillis()-startTime));" +
//                        "}"
//                , clazz);
//        clazz.addMethod(newMethod);
//        //调用修改后的 Person 类的 toString 方法
//        Person person1 = (Person) clazz.toClass().newInstance();
//        System.out.println(person1.toString());
    }

}
