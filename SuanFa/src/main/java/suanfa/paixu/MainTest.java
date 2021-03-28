package suanfa.paixu;

import static suanfa.paixu.DuiShuQi.*;
import static suanfa.paixu.OutArrTest.*;

public class MainTest {
    public static void main(String[] args) {
        int maxl = 66;
        int maxV = 99;
        int testNum = 999;
        for (int i = 0; i < testNum; i++) {
            int[] arr = getArr(maxl, maxV);
            int[] arr1 = copyArr(arr);
            int[] arr2 = copyArr(arr);
            ChaRuTest.sort(arr);
            if (i == 0) {
                out(arr);
            }
            if (!isSortArr(arr)) {
                System.out.println("ChaRuTest.sort cuo le");
            }
            MaoPaoTest.sort(arr1);
            if (!isSortArr(arr1)) {
                System.out.println("MaoPaoTest.sort cuo le");

            }
            XuanZeTest.sort(arr2);
            if (!isSortArr(arr2)) {
                System.out.println("XuanZeTest.sort cuo le");
            }
        }
    }
}
