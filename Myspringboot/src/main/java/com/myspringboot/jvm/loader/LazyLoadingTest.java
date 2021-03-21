package com.myspringboot.jvm.loader;

/**
 * 类加载过程的测试
 */
public class LazyLoadingTest {
    public static void main(String[] args) throws Exception {
        //    ATest a; //不会打印（没有加载完成）
        //    ATest a =null;//不会打印（没有加载完成）
        //    System.out.println(ATest.I);//打印出5（没有执行静态块，未加载完成）
        //    System.out.println(ATest.j);//打印出ATest，j = 11，11（静态变量赋初始值，已加载完成）
        //    Class.forName("com.myspringboot.jvm.loader.ATest");//打印出ATest，j = 11（执行静态块，已加载完成）
        //    Class.forName("com.myspringboot.jvm.loader.BTest");//打印出ATest，j = 11，BTest（执行静态块，已加载完成）
        //    BTest x = new BTest();//打印出ATest，j = 11，BTest（实例化父子类，已加载完成）
    }
}
