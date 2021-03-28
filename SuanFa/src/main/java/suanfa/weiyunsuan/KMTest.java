package suanfa.weiyunsuan;

import java.util.HashMap;
import java.util.HashSet;

public class KMTest {
    public static int km(int[] arr, int k, int m) {
        if (arr == null || arr.length < (k + m) || k >= m || m < 2) {
            return -1;
        }
        int[] bitArr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                bitArr[j] += arr[i] >> j & 1;
            }
        }
        int num = 0;
        for (int i = 0; i < bitArr.length; i++) {
            if (bitArr[i] % m != 0) {
                num |= 1 << i;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int testNum = 100000;
        int maxNum = 9;
        int maxValue = 99;
        int num = 9;
        System.out.println("start");
        for (int i = 0; i < testNum; i++) {
            int num1 = (int) (Math.random() * num) + 1;
            int num2 = (int) (Math.random() * num) + 1;
            int k = Math.min(num1, num2);
            int m = Math.max(num1, num2);
            if (k == m) {
                m++;
            }
            int[] arr = getArr(k, m, maxNum, maxValue);
            int km = km(arr, k, m);
            int km1 = getKM(arr, k, m);
            if (km != km1) {
                System.out.println("error");
                System.out.println("k = " + k);
                System.out.println("m = " + m);
                System.out.println("km = " + km);
                System.out.println("km1 = " + km1);
                out(arr);
                break;
            }
        }
        System.out.println("end");
    }

    public static int getKM(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == k) {
                return integer;
            }
        }
        return -1;
    }

    public static int[] getArr(int k, int m, int maxNum, int maxValue) {
        int kValue = getRandom(maxValue);
        int[] arr = new int[k + (maxNum - 1) * m];
        for (int i = 0; i < k; i++) {
            arr[i] = kValue;
        }
        int index = k;
        HashSet<Integer> set = new HashSet<>();
        set.add(kValue);
        while (--maxNum != 0) {
            int mValue = 0;
            do {
                mValue = getRandom(maxValue);
            } while (set.contains(mValue));
            set.add(mValue);
            for (int i = 0; i < m; i++) {
                arr[index++] = mValue;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int num = arr[i];
            arr[i] = arr[j];
            arr[j] = num;
        }
        return arr;
    }

    public static int getRandom(int maxValue) {
        return (int) ((Math.random() - Math.random()) * maxValue);
    }

    public static void out(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
