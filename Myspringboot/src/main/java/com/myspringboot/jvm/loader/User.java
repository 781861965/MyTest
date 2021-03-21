package com.myspringboot.jvm.loader;

public class User {
    private static final int i = System.currentTimeMillis() % 2L == 0 ? 2 : 1;

    static {
        System.out.println(i);
    }
}
