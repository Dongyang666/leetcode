package dp;

/**
 * @description: { 股票买卖问题}
 * @author: dyliu7@iflytek.com
 * @date: 2019/12/17 10:02
 */
public class BuyStock {
    //一共有三个状态。。今天是第几天----允许交易的次数-----当前持有状态（持有还是未持有）---
    //dp[3][3][1]    今天事第三天，允许交易次数为三次，当前是持有状态
    //要 求的 结果是dp[n-1][K][0]

    /**
     * 股票交易---带冷却周期的--不限制交易次数
     *
     * @param prices
     * @return
     */
    public static int maxProfitWithCool(int[] prices) {
        int[][] dp = new int[prices.length][2];
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;
        for (int i = 0; i < dp.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = tmp;
        }
        return dp_i_0;
    }


    /**
     * 股票交易-----不带冷却周期并且不限制交易次数
     *
     * @param prices
     * @return
     */
    public static int maxProfitWithoutCool(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }


    /**
     * 股票交易 限制交易次数---没有冷却周期
     *
     * @param prices
     * @param k
     * @return
     */
    public static int maxProfit(int[] prices, int k) {
        if (prices.length == 0 || k == 0) {
            return 0;
        }
        if (k > prices.length / 2) {
            return maxProfitWithoutCool(prices);
        }
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[0];
                    continue;
                }
                //当前未持有
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                //当前持有
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][k][0];
    }

    public static int maxProfileWithFee(int[] prices, int fee) {
        int dp_i_0 = 0, dp_i_1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i] - fee);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        System.out.println(maxProfileWithFee(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}
