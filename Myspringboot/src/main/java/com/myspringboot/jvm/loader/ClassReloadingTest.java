package com.myspringboot.jvm.loader;

import java.io.*;

public class ClassReloadingTest extends ClassLoader {


    private String pathName;

    public ClassReloadingTest(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(pathName));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int size = 0;

            while ((size = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, size);
            }
            baos.flush();
            byte[] bytes = baos.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.loadClass(name);
    }


    public static void main(String[] args) throws Exception {
        ClassReloadingTest m = new ClassReloadingTest("C:\\Users\\Administrator\\Desktop\\User.class");
        Class clazz = m.findClass("com.myspringboot.jvm.loader.User");

        m = new ClassReloadingTest("C:\\Users\\Administrator\\Desktop\\User.class");
        Class clazzNew = m.findClass("com.myspringboot.jvm.loader.User");

        System.out.println(clazz == clazzNew);
    }
}
