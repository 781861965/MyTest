package com.myspringboot.jvm.loader;


import java.io.*;

public class MyClassLoaderWithEncription extends ClassLoader {

    public static int seed = 0B10110110;


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
        MyClassLoaderWithEncription myClassLoader = new MyClassLoaderWithEncription("C:\\Users\\Administrator\\Desktop\\User.classs");
        myClassLoader.encFile("C:\\Users\\Administrator\\Desktop\\User.class");
        Class clazz = myClassLoader.loadClass("com.myspringboot.jvm.loader.User");
        Class clazz1 = myClassLoader.loadClass("com.myspringboot.jvm.loader.User");

        System.out.println(clazz == clazz1);

        //     User user = (User) clazz.newInstance();

        System.out.println(myClassLoader.getClass().getClassLoader());
        System.out.println(myClassLoader.getParent());
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.newInstance().getClass().getClassLoader());
        //    System.out.println(user.getClass().getClassLoader());
    }

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

    private byte[] getNewBuffer(byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            if (-128 < buffer[i]) {
                buffer[i] = (byte) -buffer[i];
            }
        }
        return buffer;
    }
}
