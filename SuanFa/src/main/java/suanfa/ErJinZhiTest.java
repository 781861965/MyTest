package suanfa;

public class ErJinZhiTest {
    public static void main(String[] args) {

       int num =-1;
        out32( num);
    }


    private static void out32(int num){
        for (int i = 31; i >=0 ; i--) {
            System.out.print((num & (1<<i)) == 0? 0: 1);
        }
    }

}
