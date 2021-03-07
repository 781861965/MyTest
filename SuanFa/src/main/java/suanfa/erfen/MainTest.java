package suanfa.erfen;


import static suanfa.erfen.DuiShuQi.*;

public class MainTest {
    public static void main(String[] args) {
        int maxl = 166;
        int maxV = 199;
        int testNum = 19999;
        for (int i = 0; i < testNum; i++) {
            int[] arr = getArr(maxl, maxV);
            try {
                if (!isMinIndex(arr, ZhaoshuTest.findJuBuZuiXiao(arr))) {
                    System.out.println("ZhaoshuTest.findJuBuZuiXiao cuo le");
                    break;
                }
            }catch (Exception e){
                out(arr);
                break;
            }

        }
    }
}
