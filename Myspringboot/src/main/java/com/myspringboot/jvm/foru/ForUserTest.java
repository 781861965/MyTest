package com.myspringboot.jvm.foru;

public class ForUserTest {
    //开启逃逸分析使用下面的代码
    //vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintGC  -XX:+PrintGCTimeStamps  -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintGCApplicationStoppedTime
    public static void main(String[] args) {
        //填充内存
        byte[] bytes1 = new byte[5 * 1024 * 1024];
        long start = System.currentTimeMillis();
        //考虑到业务场景，在for中获取到对象后，没有后续操作的话，没有意义；所以在new完之后，还进行了简单的操作
        outFor();
        //inFor();
        System.out.println(System.currentTimeMillis() - start);
        //强制gc
        byte[] bytes2 = new byte[5 * 1024 * 1024];
    }

    public static void outFor() {
        User user = null;
        for (int i = 0; i < 999999999; i++) {
            user = new User();
            user.setI(i);
            user.getI();
        }
    }

    public static void inFor() {
        for (int i = 0; i < 999999999; i++) {
            User user = new User();
            user.setI(i);
            user.getI();
        }
    }

//分隔线=====================================================================================================

    //关闭逃逸分析使用下面的代码
    //vm参数：-server -XX:-DoEscapeAnalysis -verbose:gc -Xms400M -Xmx400M -Xmn300M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+PrintGC  -XX:+PrintGCTimeStamps  -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintGCApplicationStoppedTime
//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        //考虑到业务场景，在for中获取到对象后，没有后续操作的话，没有意义；所以在new完之后，还进行了简单的操作
//        outFor();
//        //inFor();
//        System.out.println(System.currentTimeMillis() - start);
//        //强制gc
//        byte[] bytes2 = new byte[200 * 1024 * 1024];
//    }
//
//    public static void outFor() {
//        User user = null;
//        for (int i = 0; i < 9999999; i++) {
//            user = new User();
//            user.setI(i);
//            user.getI();
//        }
//    }
//
//    public static void inFor() {
//        for (int i = 0; i < 9999999; i++) {
//            User user = new User();
//            user.setI(i);
//            user.getI();
//        }
//    }
}
