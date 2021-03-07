package com.myspringboot.jvm.loader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {


    private String pathName;

    public MyClassLoader(String pathName) {
        this.pathName = pathName;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(pathName));
             ByteArrayOutputStream baos = new ByteArrayOutputStream();){
            byte[] buffer = new byte[1024];
            int size = 0;

            while ((size = fis.read(buffer)) != -1) {
                baos.write(buffer,0,size);
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
        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\Administrator\\Desktop\\User.class");
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
}
