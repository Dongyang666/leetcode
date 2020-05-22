package dp;

/**
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 *
 * 请你按下面的要求分割字符串：
 *
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", k = 2
 * 输出：1
 * 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
 * 示例 2：
 *
 * 输入：s = "aabbc", k = 3
 * 输出：0
 * 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
 * 示例 3：
 *
 * 输入：s = "leetcode", k = 8
 * 输出：0
 *
 * @author liudongyang
 * @date 2020/05/19
 */
public class PalindromePartition {
    public static int palindromePartition(String s, int k) {
        char[] chars = s.toCharArray();
        /**
         * 优化cost函数，使用区间dp
         */
        int[][] help = new int[chars.length][chars.length];

        for (int i = chars.length; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                if (j - i > 1) {
                    help[i][j] = help[i + 1][j - 1];
                }
                if (chars[i] != chars[j]) {
                    help[i][j]++;
                }
            }
        }

        int[][] dp = new int[chars.length + 1][k + 1];
        for (int i = 0; i <= chars.length; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    //dp[i][j] = cost(chars, 0, i - 1);
                    dp[i][j] = help[0][i - 1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    // 枚举遍历
                    for (int em = j - 1; em < i; em++) {
                        //dp[i][j] = Math.min(dp[i][j], dp[em][j - 1] + cost(chars, em, i - 1));
                        
                        dp[i][j] = Math.min(dp[i][j], dp[em][j - 1] + help[em][i - 1]);
                    }
                }
            }
        }
        return dp[chars.length][k];
    }


    /**
     * 求指定l-r之间最少需要修改几个字符串可以使l-r是回文
     *
     * @param chars
     * @param l
     * @param r
     * @return
     */
    private static int cost(char[] chars, int l, int r) {
        int ans = 0;
        while (l < r) {
            if (chars[l++] != chars[r--]) {
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(palindromePartition("leetcode", 2));
    }


}
