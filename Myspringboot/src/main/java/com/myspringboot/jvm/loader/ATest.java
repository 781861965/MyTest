package com.myspringboot.jvm.loader;

public class ATest {
    static final int I = 5;
    static int j = 11;

    static {
        System.out.println("ATest");
        System.out.println("j = " + j);
    }
}
