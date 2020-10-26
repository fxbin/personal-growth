package cn.fxbin.record.basic.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ProxyUtils
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/26 10:42
 */
public class ProxyUtils {

    public static boolean saveProxyClass(String path, String proxyClassName, Class[] interfaces) {
        if (proxyClassName == null || path == null) {
            return false;
        }
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyClassName, interfaces);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
