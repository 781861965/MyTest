package suanfa.erfen;

public class ZhaoshuTest {

    public static void main(String[] args) {
        int[] ints = {1, 2, 2, 2, 3, 4, 5, 6, 6, 6, 7, 8, 8, 9, 10};

        System.out.println(findZuiZuo(ints, 6));
        System.out.println(findZuiZuo(ints, 2));
        System.out.println(findZuiZuo(ints, 8));
        System.out.println(findZuiZuo(ints, 18));
        System.out.println(findJuBuZuiXiao(new int[]{37, 33, 78, 27, 23, 11, 30, 79, 96, 93, 26, 83, 34, 30, 83, 75, 70, 77, 83, 97, 92, 30, 38, 37, 60, 27, 61, 96, 8, 51}));
        System.out.println(findJuBuZuiXiao(ints));
        System.out.println(findJuBuZuiXiao(ints));
        System.out.println(findJuBuZuiXiao(ints));

    }

    private static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        int m = r / 2;
        while (l < m || m < r) {
            if (num == arr[m]) {
                return true;
            } else if (num < arr[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
            m = (l + r) / 2;
        }
        return false;
    }

    private static int findZuiZuo(int[] arr, int num) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index;
        }
        int l = 0;
        int r = arr.length - 1;
        int m = r / 2;

        while (l < m || m < r) {
            if (arr[m] >= num) {
                index = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
            m = (l + r) / 2;
        }
        return index;
    }

    public static int findJuBuZuiXiao(int[] arr) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 0;
        int r = arr.length - 1;
        int m = r / 2;

        while (l < m || m < r) {
            if (arr[m] < arr[m - 1] && arr[m] < arr[m + 1]) {
                return m;
            } else if (arr[m] > arr[m - 1]) {
                r = m;
            } else {
                l = m;
            }
            m = (l + r) / 2;
        }
        return index;
    }
}
