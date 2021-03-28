package xunhuan;

public class ForTest {
    public static void main(String[] args) {

        a:
        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);
            for (int j = 0; j < 3; j++) {
                System.out.println("j = " + j);
                for (int k = 0; k < 3; k++) {
                    System.out.println("k = " + k);
                    continue a;
                }
                System.err.println("j = " + j);
            }
            System.err.println("i = " + i);
        }

        System.out.println("=============================");

        a:
        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);
            for (int j = 0; j < 3; j++) {
                System.out.println("j = " + j);
                break a;
            }
            System.err.println("i = " + i);
        }
    }
}
