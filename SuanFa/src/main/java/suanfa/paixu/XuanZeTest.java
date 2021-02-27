package suanfa.paixu;

public class XuanZeTest {
    public static void main(String[] args) {

        int[] arr = {6,5,6,4,6,5,4,1,1,9,8,7};
        out(arr);
        System.out.println();
        sort(arr);
        out(arr);
    }

    public static void sort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int minI =i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[minI]>arr[j]){
                    minI = j;
                }
            }
            int num = arr[i];
            arr[i] = arr[minI];
            arr[minI] = num;

        }

    }
    private static void out(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
