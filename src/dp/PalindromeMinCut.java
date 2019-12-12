package dp;

import java.util.Arrays;

/**
 * @author liudongyang
 * 给定一个字符串，请把字符串分割，分割出来的每一个子串，都要求是回文串。 请问最少分割成几部分可以完成这个要求。
 */
public class PalindromeMinCut {
    private static int minCut(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean[][] d = getPalindromeDp(str);
        int[] dp = new int[len + 1];
        dp[len] = -1;
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (chars[i] == chars[j] && (j - i < 2 || d[i + 1][j - 1])) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }

    private static boolean[][] getPalindromeDp(String str) {
        boolean[][] dp = new boolean[str.length()][str.length()];
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        int col = 1;
        while (col < str.length()) {
            int i = 0;
            int j = col;
            while (j < str.length()) {
                dp[i][j] = chars[i] == chars[j] ? dp[i + 1][j - 1] : false;
                i++;
                j++;
            }
            col++;
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "121121";
        System.out.println(p(str, 0));
        System.out.println(minCut(str));
    }


    private static int p(String str, int len) {
        if (len == str.length()) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = len; i < str.length(); i++) {
            boolean subStrResult = isPalindrome(str, len, i);
            if (subStrResult) {
                res = Math.min(res, p(str, i + 1));
            }
        }
        System.out.println(len + "----" + res);
        return res + 1;
    }

    private static boolean isPalindrome(String str, int left, int right) {
        while (left <= right && str.charAt(left++) != str.charAt(right--)) {
            return false;
        }
        return true;
    }

}
