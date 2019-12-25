package dp;

import array.ArraysUtils;

import java.util.Arrays;

/**
 * @description: { 戳气球}
 * @author: dyliu7@iflytek.com
 * @date: 2019/12/23 10:39
 */
public class PokeBalloon {
    public static int pokeBalloon(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? 1 : nums[i - 1];
            int right = i == nums.length - 1 ? 1 : nums[i + 1];
            int subRes = pokeBalloon(pokeOneBallon(nums, i));
            res = Math.max(res, left * nums[i] * right + subRes);
        }
        return res;
    }

    public static int pokeBalloonDp(int[] nums) {
        int[] tmp = new int[nums.length + 2];
        int n = nums.length;
        System.arraycopy(nums, 0, tmp, 1, nums.length);
        nums = tmp;
        nums[0] = 1;
        nums[nums.length - 1] = 1;
        int[][] dp = new int[nums.length][nums.length];
        for (int c = 1; c <= n; c++) {
            for (int i = 1; i + c - 1 <= n; i++) {
                int j = i + c - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], nums[i - 1] * nums[k] * nums[j + 1] + dp[i][k - 1] + dp[k + 1][j]);
                }
            }
        }
        ArraysUtils.printArray(dp);
        return dp[1][n];
    }

    public static int[] pokeOneBallon(int[] nums, int index) {
        if (nums.length == 0 || index < 0 || index > nums.length - 1) {
            return nums;
        }
        int[] res = new int[nums.length - 1];
        int[] left = Arrays.copyOfRange(nums, 0, index);
        int[] right = Arrays.copyOfRange(nums, index + 1, nums.length);
        System.arraycopy(left, 0, res, 0, left.length);
        System.arraycopy(right, 0, res, left.length, right.length);
        return res;
    }

    public static void main(String[] args) {

        System.out.println(pokeBalloonDp(new int[]{3, 1, 5, 8}));
    }
}
