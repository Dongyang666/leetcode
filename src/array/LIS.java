package array;

import java.util.Arrays;

/**
 * @description: {最长递增子序列} Longest Increasing Subsequence
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/05/13 19:32
 */
public class LIS {
	private static int[] getdp(int[] arr) {
		int[] dp = new int[arr.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					//treemap
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}
		return dp;
	}

	private static int[] generateLIS(int[] arr, int[] dp) {
		int index = 0;
		int len = 0;
		//获取最长递增子序列的最后一个位置和最长子序列的数量
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > len) {
				index = i;
				len = dp[i];
			}
		}

		int[] lis = new int[len];
		for (int i = index; i >= 0; i--) {
			if (arr[i] <= arr[index] || dp[i] == dp[index] - 1) {
				lis[--len] = arr[i];
				index = i;
			}
		}

		return lis;
	}

	public static void main(String[] args) {
		int[] ints = {2, 1, 5, 3, 6, 4, 8, 9, 7};

		System.out.println(Arrays.toString(generateLIS(ints, getdp(ints))));
	}
}
