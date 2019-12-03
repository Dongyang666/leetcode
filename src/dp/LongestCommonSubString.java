package dp;


import java.util.Arrays;

/**
 * 最长公共子串
 *
 * @author liudongyang
 */
public class LongestCommonSubString {
	/**
	 * 空间复杂度 o（n*m）
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static String getLcs1(String str1, String str2) {
		if (str1.length() == 0 || str2.length() == 0) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int max = 1;
		int index = -1;
		int[][] dp = new int[chs1.length][chs2.length];
		for (int i = 0; i < chs1.length; i++) {
			for (int j = 0; j < chs2.length; j++) {
				dp[i][j] = chs1[i] == chs2[j]
						? (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1] + 1 : 0;
				if (dp[i][j] > max) {
					max = dp[i][j];
					index = i;
				}
			}
		}
		return index == -1 ? null : str1.substring(index - max + 1, index + 1);
	}

	/**
	 * 空间复杂度O(min(n,m))
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static String getLcs2(String str1, String str2) {
		if (str1.length() == 0 || str2.length() == 0) {
			return "";
		}
		char[] chs1 = str1.toCharArray().length > str2.toCharArray().length ? str2.toCharArray() : str1.toCharArray();
		char[] chs2 = str1.toCharArray().length > str2.toCharArray().length ? str1.toCharArray() : str2.toCharArray();
		int preLeftValue = 0;
		int pre = 0;
		int[] dp = new int[chs1.length];
		int max = 0;
		int index = -1;
		for (int i = 0; i < chs2.length; i++) {
			for (int j = 0; j < chs1.length; j++) {
				pre = dp[j];
				dp[j] = chs1[j] == chs2[i] ? preLeftValue + 1 : 0;
				if (dp[j] > max) {
					max = dp[j];
					index = j;
				}
				preLeftValue = (i == 0 || j == 0) ? 0 : pre;
			}
		}
		return index == -1 ? null : new String(Arrays.copyOfRange(chs1, index - max + 1, index + 1));
	}

	private static String getLcs3(String str1, String str2) {
		if (str1.length() == 0 || str2.length() == 0) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int max = 0;
		int end = -1;
		int col = chs2.length - 1;
		int row = 0;
		while (row < chs1.length) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < chs1.length && j < chs1.length) {
				if (chs1[i] != chs2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return str1.substring(end - max + 1, end + 1);

	}

	public static void main(String[] args) {
		System.out.println(getLcs1("0123bacd", "er3123ba"));
		System.out.println(getLcs2("0000123bacd", "er3123ba"));
	}
}
