package cn.fxbin.record.basic.proxy.dynamic.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * Person
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 11:10
 */
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name=" + this.name;
    }

}
