package cn.fxbin.record.basic.proxy.dynamic.asm;


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * AsmMain
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 11:58
 */
public class AsmMain extends ClassLoader implements Opcodes {

    public static void main(String[] args) throws Exception {
        // 创建一个 ClassWriter , 以生成一个新的类
        ClassWriter classWriter = new ClassWriter(0);
        // V1_6 是生成的 class 的版本号  ACC_PUBLIC 是类访问修饰符
        classWriter.visit(V1_6, ACC_PUBLIC, "cn/fxbin/record/basic/proxy/dynamic/asm/Person", null, "java/lang/Object", null);

        // 生成构造方法，因此从这里可以看出，如果类中没有构造方法，系统会给我们一个默认的构造方法
        MethodVisitor mw = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
                null);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(1, 1);
        mw.visitEnd();

        // 添加字段， public 访问类型
        FieldVisitor fv = classWriter.visitField(ACC_PUBLIC, "name", "Ljava/lang/String;", null, null);
        fv.visitEnd();

        // 转换成 Class 对象
        byte[] code = classWriter.toByteArray();
        AsmMain loader = new AsmMain();
        Class<?> clazz = loader.defineClass(null, code, 0, code.length);

        // 通过默认构造函数创建对象
        Object beanObj = clazz.getConstructor().newInstance();
        // 为成员变量 name 赋值 Limynl
        clazz.getField("name").set(beanObj, "fxbin");
        String nameString = (String) clazz.getField("name").get(beanObj);
        System.out.println("filed value : " + nameString);
    }

}
