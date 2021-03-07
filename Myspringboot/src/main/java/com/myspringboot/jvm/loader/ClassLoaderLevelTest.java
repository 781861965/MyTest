package com.myspringboot.jvm.loader;

public class ClassLoaderLevelTest {
  public static void main(String[] args) {
    System.out.println(String.class.getClassLoader());
    System.out.println(sun.awt.HKSCS.class.getClassLoader());
    System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
//    System.out.println(User.class.getClassLoader());
//
//    System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
//    System.out.println(User.class.getClassLoader().getClass().getClassLoader());

    System.out.println(ClassLoader.getSystemClassLoader());


    System.out.println("====================================================================");

    System.out.println(ClassLoaderLevelTest.class.getClassLoader());
    System.out.println(ClassLoaderLevelTest.class.getClassLoader().getClass().getClassLoader());
    System.out.println(ClassLoaderLevelTest.class.getClassLoader().getParent());
    System.out.println(ClassLoaderLevelTest.class.getClassLoader().getParent().getParent());
    //System.out.println(ClassLoaderLevelTest.class.getClassLoader().getParent().getParent().getParent());
  }
}
