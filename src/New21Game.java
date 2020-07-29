import java.util.HashMap;
import java.util.Map;

/**
 * @author liudongyang
 * @date 2020/05/21
 */
public class New21Game {
    private static int i = 0;

    public static void main(String[] args) {
        System.out.println(new21Game(5710, 5070, 8516));
    }

    public static double new21Game2(int N, int K, int W) {
        return dfs(N, K, W, 0, new HashMap<>());
    }


    public static double dfs(int N, int K, int W, int cur, Map<Integer, Double> map) {
        if (cur >= K && cur <= N) {
            return 1;
        }
        if (cur > N) {
            return 0;
        }
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        double res = 0;
        for (int i = 1; i <= W; i++) {
            // 选择其中一个 的概率
            double d = dfs(N, K, W, cur + i, map);
            double c = (double) 1 / W;
            res += d * c;

        }
        map.put(cur, res);
        return res;
    }


    public static double new21Game(int N, int K, int W) {
        // dp[i] 含义爱丽丝的分数是i时，不超过N的概率
        double[] dp = new double[K + W];
        // 当前的分数已经大于K 并且当前分数小于等于N 直接设置概率为1
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }

        /**
         * 前缀和优化
         * dp[x] =     (dp[x + 1] + dp[x + 2] + dp[x + 3] ... +dp[x + w]) / W
         * dp[x + 1] = (dp[x + 2] + dp[x + 3] + dp[x + 4] ... +dp[x + 1 + w]) /W
         * dp[]
         */
        dp[K - 1] = 1.0 * Math.min(N - K + 1, W) / W;
        for (int i = K - 2; i > -1; --i) {
            /*for (int j = 1; j <= W; j++) {
                dp[i] += dp[i + j] / W;
            }*/
            //dp[i] = ((dp[i + 1] * W) + dp[i + 1] - dp[i + 1 + W]) / W;
            dp[i] = dp[i + 1] + (dp[i + 1] - dp[i + 1 + W]) / W;
        }
        return dp[0];
    }
}
