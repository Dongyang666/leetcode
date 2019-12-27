package dp;

import java.util.Arrays;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JoinMaxNumber {

    public static int[] dp(int[] num1, int[] num2, int kx) {
        long[][][] dp = new long[num1.length + 1][num2.length + 1][kx + 1];
        //init
        for (int i = 1; i <= num2.length; i++) {
            for (int j = 1; j <= kx; j++) {
                dp[0][i][j] = Math.max(dp[0][i - 1][j - 1] * 10 + num2[i - 1], dp[0][i - 1][j]);
            }
        }
        //init
        for (int i = 1; i <= num1.length; i++) {
            for (int j = 1; j <= kx; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j - 1] * 10 + num1[i - 1], dp[i - 1][0][j]);
            }
        }
        for (int i = 1; i <= num1.length; i++) {
            for (int j = 1; j <= num2.length; j++) {
                for (int k = 1; k <= kx; k++) {
                    long selectA = (dp[i - 1][j][k - 1] * 10 + num1[i - 1]);
                    long selectB = (dp[i][j - 1][k - 1] * 10 + num2[j - 1]);
                    long selectNot = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
                    dp[i][j][k] = Math.max(selectA, Math.max(selectB, selectNot));
                }
            }
        }
        int[] ints = new int[kx];
        String res = String.valueOf(dp[num1.length][num2.length][kx]);
        System.out.println(res);
        for (int i = ints.length - 1; i >= 0; i--) {
            ints[i] = res.charAt(i) - '0';
        }
        return ints;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dp(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));
        int[][] ints = new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        System.out.println(Arrays.toString(ints[1]));
    }
}
