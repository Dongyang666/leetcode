package array;

/**
 * 最大子矩阵和
 * 给定一个矩阵，矩阵中每个值可正、可负、可0。求最大子矩阵和是多少
 * 矩阵压缩问题
 */
public class MaxSubMatrixSum {

    private static int maxSubMatrixSum(int[][] arr) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] sumArray = null;
        for (int i = 0; i < arr.length; i++) {
            //每一次i更新的时候就是一个开始 累加数组清空
            sumArray = new int[arr[0].length];
            for (int j = i; j < arr.length; j++) {
                for (int k = 0; k < sumArray.length; k++) {
                    sumArray[k] += arr[j][k];
                    cur += sumArray[k];
                    Math.max(cur, max);
                    cur = cur <= 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

    /**
     * 求最大子数组和是多少
     *
     * @param arr
     * @return
     */
    private static int getMaxSubArray(int[] arr) {
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int a : arr) {
            cur += a;
            max = Math.max(max, cur);
            cur = cur <= 0 ? 0 : cur;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSubArray(new int[]{1, -2, 3, 5, -2, 6, -1}));
    }
}
