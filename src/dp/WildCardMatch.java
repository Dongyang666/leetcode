package dp;

import java.util.Arrays;

/**
 * @description: {给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 }
 * @author: dyliu7@iflytek.com
 * @date: 2019/12/25 16:10
 */
public class WildCardMatch {
    public static boolean isMatch(String s, String p) {
        return rec(s, 0, p, 0);
    }

    private static boolean rec(String s, int lenA, String str2, int lenP) {
        if (lenP == str2.length()) {
            return lenA == s.length();
        }
        if (lenA == s.length()) {
            return rec(s, lenA, str2, lenP + 1);
        }
        char p = str2.charAt(lenP);
        boolean res = false;
        if (p == '*') {
            //*三种策略匹配的是空字符串。匹配了任意字符串。匹配了一个字符串
            return rec(s, lenA + 1, str2, lenP) || rec(s, lenA + 1, str2, lenP + 1) || rec(s, lenA, str2, lenP + 1);
        } else if (p == '?') {
            return rec(s, lenA + 1, str2, lenP + 1);
        }
        if (str2.charAt(lenP) == s.charAt(lenA)) {
            return rec(s, lenA + 1, str2, lenP + 1);
        } else {
            return false;
        }
    }

    private static boolean dp(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        if (s == "" && p == "*") {
            return true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                char charS = s.charAt(i);
                char charP = p.charAt(j);
                if (charP == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + 1] || dp[i][j + 1];
                } else if (charP == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = charS == charP ? dp[i + 1][j + 1] : false;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public static void main(String[] args) {

        System.out.println(isMatch("a", "a*****"));
    }
}
