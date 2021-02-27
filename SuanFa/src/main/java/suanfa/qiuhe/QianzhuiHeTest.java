package suanfa.qiuhe;

public class QianzhuiHeTest {
    public static void main(String[] args) {
        int[] arr = {6, 5, 6, 4, 6, 5, 4, 1, 1, 9, 8, 7};

        int[] sumArr = getSumArr(arr);

        System.out.println(rangeSum(sumArr, 3, 6));


    }

    private static int rangeSum(int[] arr, int l, int r) {
        if (arr == null || arr.length < r || l < 0 || l > r) {
            return -1;
        }
        if (l == 0) {
            return arr[r];
        } else {

            return arr[r] - arr[l - 1];
        }

    }

    private static int[] getSumArr(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        return arr;
    }


}
