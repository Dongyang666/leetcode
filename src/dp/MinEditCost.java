package dp;

/**
 * @description: { 最少编辑代价}
 * @author: dyliu7@iflytek.com
 * @date: 2019/9/5 16:50
 */
public class MinEditCost {
	public static void main(String[] args) {
		//

		System.out.println(minConst("abc", "abd", 1, 1, 1));
	}

	private static int minConst(String str1, String str2, int ic, int dc, int rc) {
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int col = chs2.length + 1;
		int row = chs1.length + 1;
		int[][] dp = new int[row][col];
		// 初始化列
		for (int i = 0; i < row; i++) {
			dp[i][0] = i * dc;
		}
		// 初始化行
		for (int i = 0; i < col; i++) {
			dp[0][i] = i * ic;
		}
		for (int i = 1; i < col; i++) {
			for (int j = 1; j < row; j++) {
				// 删除掉str2的字符,即可匹配
				int delStr2Cost = dc + dp[i - 1][j];
				// 插入当前str2的新字符，即可匹配
				int insertStr2Cost = ic + dp[i][j - 1];
				// 如果str2和str1的前一位相同则
				if (chs1[i - 1] == chs2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + rc;
				}
				dp[i][j] = Math.min(dp[i][j], delStr2Cost);
				dp[i][j] = Math.min(dp[i][j], insertStr2Cost);
			}
		}
		return dp[row - 1][col - 1];
	}
}
