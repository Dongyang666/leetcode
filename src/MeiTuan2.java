import java.util.Scanner;

/**
 * @author liudongyang
 * 你打开了美了么外卖，选择了一家店，你手里有一张满X元减10元的券，店里总共有n种菜，第i种菜一份需要A_i元，因为你不想吃太多份同一种菜，所以每种菜你最多只能点一份，现在问你最少需要选择多少元的商品才能使用这张券。
 * <p>
 * <p>
 * 输入描述:
 * 第一行两个正整数n和X，分别表示菜品数量和券的最低使用价格。（1≤n≤100, 1≤X≤10000） 接下来一行n个整数，第i个整数表示第i种菜品的价格。（1≤A_i≤100）
 * <p>
 * 输出描述:
 * 一个数，表示最少需要选择多少元的菜才能使用这张满X元减10元的券，保证有解。
 * <p>
 * 输入例子1:
 * 5 20
 * 18 19 17 6 7
 * <p>
 * 输出例子1:
 * 23
 */
public class MeiTuan2 {
	public static int process(int[] prices, int aim) {
		int sum = 0;
		for (int i = 0; i < prices.length; i++) {
			sum += prices[i];
		}
		int[][] dp = new int[prices.length + 1][aim + 1];
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = sum;
		}
		for (int i = 1; i <= prices.length; i++) {
			for (int j = 0; j <= aim; j++) {
				if (j <= prices[i - 1]) {
					dp[i][j] = Math.min(prices[i - 1], dp[i - 1][j]);
				} else {
					dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - prices[i - 1]] + prices[i - 1]);
				}

			}
		}
		return dp[prices.length][aim];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int aim = sc.nextInt();
		int[] prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = sc.nextInt();
		}
		System.out.println(process(prices, aim));
	}
}
