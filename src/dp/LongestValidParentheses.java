package dp;

/**
 * @author liudongyang
 * 最长有效括号
 */
public class LongestValidParentheses {
    public static int solution(String str) {
        int[] dp = new int[str.length()];
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            if (str.charAt(i) == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(solution(")()())"));
    }
}
