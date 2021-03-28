package suanfa.paixu;

public class JiaoHuanArr {
    public static int[] jiaohuan(int[] arr, int i, int j) {
        int num = arr[i];
        arr[i] = arr[j];
        arr[j] = num;
        return arr;
    }
}
