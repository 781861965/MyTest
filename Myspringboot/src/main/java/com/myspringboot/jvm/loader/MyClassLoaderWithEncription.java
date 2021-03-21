package com.myspringboot.jvm.loader;


import java.io.*;

/**
 * 带加密功能的自定义类加载器（打破双亲委派机制，实现热加载）
 */
public class MyClassLoaderWithEncription extends ClassLoader {

    private String pathName;

    public MyClassLoaderWithEncription(String pathName) {
        this.pathName = pathName;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(pathName));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int size = 0;

            while ((size = fis.read(buffer)) != -1) {
                baos.write(getNewBuffer(buffer), 0, size);
            }
            baos.flush();
            byte[] bytes = baos.toByteArray();

            return defineClass(className, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(className); //throws ClassNotFoundException
    }

    public static void main(String[] args) throws Exception {
        loadsWithEnc();
        reloads();
    }

    private static void loadsWithEnc() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //加密后的.classs文件的输出位置
        MyClassLoaderWithEncription myClassLoader = new MyClassLoaderWithEncription("C:\\Users\\Administrator\\Desktop\\User.classs");
        //编译后的.class文件的存放位置
        myClassLoader.encFile("C:\\Users\\Administrator\\Desktop\\User.class");
        Class clazz = myClassLoader.findClass("com.myspringboot.jvm.loader.User");
        MyClassLoaderWithEncription myClassLoader1 = new MyClassLoaderWithEncription("C:\\Users\\Administrator\\Desktop\\User.classs");
        Class clazz1 = myClassLoader1.findClass("com.myspringboot.jvm.loader.User");
        System.out.println(clazz);
        System.out.println(clazz == clazz1);

        System.out.println(myClassLoader.getClass().getClassLoader());
        System.out.println(myClassLoader.getParent());
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.newInstance().getClass().getClassLoader());
    }

    //将加密后的数据写到.classs文件中
    private void encFile(String name) {
        try (FileInputStream fis = new FileInputStream(new File(name));
             FileOutputStream fos = new FileOutputStream(new File(pathName));) {
            byte[] buffer = new byte[1024];
            int size = 0;

            while ((size = fis.read(buffer)) != -1) {
                fos.write(getNewBuffer(buffer), 0, size);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //加密算法，其实就是对每个数取反了一下
    private byte[] getNewBuffer(byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) ~buffer[i];
        }
        return buffer;
    }

    // 可以将下面两端代码分别放开和注释掉，会得到不同的结果
    private static void reloads() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (int i = 0; i < 100; i++) {
            MyClassLoaderWithEncription myClassLoader = new MyClassLoaderWithEncription("C:\\Users\\Administrator\\Desktop\\User.classs");
            myClassLoader.encFile("C:\\Users\\Administrator\\Desktop\\User.class");

            //调用loadClass方法，遵循双亲委派，实际为复用自己的父加载器就是AppClassLoader
            Class clazz = myClassLoader.loadClass("com.myspringboot.jvm.loader.User");
            System.out.println(clazz.newInstance().getClass().getClassLoader());

            //调用findClass方法，打破双亲委派，实现热更新，加载器为MyClassLoaderWithEncription
            //Class clazz = myClassLoader.findClass("com.myspringboot.jvm.loader.User");
            //clazz.newInstance().getClass().getClassLoader();
        }
    }
}
