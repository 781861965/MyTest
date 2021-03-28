package suanfa.paixu;

public class DuiShuQi {
    public static int[] getArr(int maxL, int maxV) {
        int l = (int) (Math.random() * maxL);
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * maxV);
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static boolean isSortArr(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}
