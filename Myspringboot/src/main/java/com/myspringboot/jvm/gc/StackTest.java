package com.myspringboot.jvm.gc;

public class StackTest {
    static int  i =  0;
    public static void main(String[] args) {
        go();
    }

    static void go(){
        System.out.println(++i);
        go();
    }
}
