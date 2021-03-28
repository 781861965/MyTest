package suanfa.shuzu;

public class GetMaxDiGui {

    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mindol = l + ((r - l) >> 1);
        return Math.max(process(arr, l, mindol), process(arr, mindol + 1, r));
    }

	public static void main(String[] args) {
		int[]  arr =   new int[]{1,2,3,5,7,9,1,4,6,5,2,4,3,6};
		System.out.println(getMax(arr));
	}

}
