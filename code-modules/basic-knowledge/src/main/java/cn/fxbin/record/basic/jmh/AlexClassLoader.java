package cn.fxbin.record.basic.jmh;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * AlexClassLoader
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/20 16:42
 */
public class AlexClassLoader extends URLClassLoader {

    private final byte[] bytes;

    public AlexClassLoader(byte[] bytes) {
        super(new URL[0], ClassLoader.getSystemClassLoader());
        this.bytes = bytes;
    }

    @Override
    protected Class<?> findClass(String name) {
        return super.defineClass(name, bytes, 0, bytes.length);
    }

}
