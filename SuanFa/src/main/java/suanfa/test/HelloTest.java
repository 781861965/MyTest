package suanfa.test;

import java.math.BigInteger;

public class HelloTest {
    public static void main(String[] args) {
        System.out.println(~Integer.MAX_VALUE);
        System.out.println(~Integer.MIN_VALUE);
        System.out.println(true ^ false);
        System.out.println(new BigInteger(new byte[]{1,2,3}));
        System.out.println("Hello  World!");
    }

}
