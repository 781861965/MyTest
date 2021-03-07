package suanfa.erfen;

public class DuiShuQi {
    public static int[] getArr(int maxL, int maxV) {
        int l = (int) (Math.random() * maxL);
        int[] arr = new int[l];
        if (l > 0) {
            arr[0] = (int) (Math.random() * maxV);
        }
        for (int i = 1; i < l; i++) {
            do {
                arr[i] = (int) (Math.random() * maxV);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static boolean isMinIndex(int[] arr, int minIndex) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index == minIndex;
        }
        if (arr.length == 1) {
            return 0 == minIndex;
        }
        if (arr[0] < arr[1]) {
            return 0 == minIndex;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1 == minIndex;
        }

        if (arr[minIndex] < arr[minIndex - 1] && arr[minIndex] < arr[minIndex + 1]) {
            return true;
        }
        return index == minIndex;
    }

    public static boolean isSortArr(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void out(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
