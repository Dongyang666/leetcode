package dp;

/**
 * @author liudongyang
 * 股票的最佳交易时间
 */
public class MaxProfit {
	/**
	 * 递归
	 *
	 * @param prices
	 * @return
	 */
	private static int process(int[] prices, int s) {
		if (s >= prices.length) {
			return 0;
		}
		int max = 0;
		for (int start = s; start < prices.length; start++) {
			int maxTmp = 0;
			for (int i = start + 1; i < prices.length; i++) {
				if (prices[i] > prices[start]) {
					maxTmp = Math.max(maxTmp, process(prices, i + 1) + prices[i] - prices[start]);
				}
			}
			max = Math.max(max, maxTmp);

		}
		return max;
	}
}
