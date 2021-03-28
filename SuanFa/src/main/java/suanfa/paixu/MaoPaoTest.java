package suanfa.paixu;

public class MaoPaoTest {
    public static void main(String[] args) {
        int[] arr = {6, 5, 6, 4, 6, 5, 4, 1, 1, 9, 8, 7};
        out(arr);
        System.out.println();
        sort(arr);
        out(arr);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int num = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = num;
                }
            }
        }
    }

    private static void out(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
