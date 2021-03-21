package suanfa.weiyunsuan;

// 测试链接：https://leetcode.com/problems/divide-two-integers
public class BitAddMinusMultiDivTest {
    public static void main(String[] args) {
        System.out.println(add(0, Integer.MAX_VALUE));
        System.out.println(minus(0, Integer.MIN_VALUE));
        System.out.println(multi(9, 6));
        System.out.println(divide(Integer.MIN_VALUE, -1));
    }

    public static int add(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return a;
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int negNum(int n) {
        return add(~n, 1);
    }

    public static int multi(int a, int b) {
        int num = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                num = add(num, a);
            }
            a = a << 1;
            b = b >>> 1;
        }
        return num;
    }


    public static int divide(int a, int b) {
        if (a == b) {
            return 1;
        }
        if (b == Integer.MIN_VALUE) {
            return 0;
        }
        if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            int c = div(add(a, 1), b);
            return add(c, div(minus(a, multi(b, c)), b));
        }
        return div(a, b);
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int num = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if (x >> i >= y) {
                num |= 1 << i;
                x = minus(x, multi(y, 1 << i));
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(num) : num;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }
}
