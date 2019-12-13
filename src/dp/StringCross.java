package dp;

import java.util.Arrays;

/**
 * @author liudongyang
 * 字符串的交错组成
 * 【题目】 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有 字符，
 * 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的 字符之间保持原来在str2中的顺序
 * ，那么称aim是str1和str2的交错组成。实现 一个函数，判断aim是否是str1和str2交错组成。
 * 1）如果没有重复值则可以直接使用外排实现
 * 2）如果有重复值则不能通过外排实现必须要使用动态规划
 */
public class StringCross {
    public static boolean isStringCross(String str1, String str2, String aim) {
        if (aim.length() != str1.length() + str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] aims = aim.toCharArray();

        System.out.println(dp(chars1, chars2, aims));
        return rec(chars1, chars1.length - 1, chars2, chars2.length - 1, aims, aims.length - 1);
    }

    /**
     *
     */
    private static boolean rec(char[] chars1, int len1, char[] chars2, int len2, char[] aims, int lenA) {
        if (len1 == -1) {
            return Arrays.equals(
                    Arrays.copyOfRange(chars2, 0, len2 + 1),
                    Arrays.copyOfRange(aims, 0, lenA + 1));
        }
        if (len2 == -1) {
            //len1 = len1 == -1 ? 0 : len1;
            return Arrays.equals(
                    Arrays.copyOfRange(chars1, 0, len1 + 1),
                    Arrays.copyOfRange(aims, 0, lenA + 1));
        }
        if (lenA == -1) {
            return len1 == -1 && len2 == -1;
        }
        if (aims[lenA] == chars1[len1] && aims[lenA] == chars2[len2]) {
            return rec(chars1, len1 - 1, chars2, len2, aims, lenA - 1) |
                    rec(chars1, len1, chars2, len2 - 1, aims, lenA - 1);
        }
        if (aims[lenA] == chars1[len1]) {
            return rec(chars1, len1 - 1, chars2, len2, aims, lenA - 1);
        }
        if (aims[lenA] == chars2[len2]) {
            return rec(chars1, len1, chars2, len2 - 1, aims, lenA - 1);
        }
        return false;
    }


    public static boolean dp(char[] str1, char[] str2, char[] aim) {
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        //init 列
        for (int i = 1; i <= str1.length; i++) {
            if (aim[i - 1] != str1[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        //init 行
        for (int i = 1; i <= str2.length; i++) {
            if (aim[i - 1] != str2[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if ((str1[i - 1] == aim[i + j - 1] && dp[i - 1][j])
                        || (str2[j - 1] == aim[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }
    //空间压缩。O(n)

    public static void main(String[] args) {
        System.out.println(isStringCross("abc", "b12abd", "ab12abbcc"));
    }
}
