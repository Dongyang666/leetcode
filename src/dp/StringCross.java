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
		return rec(chars1, chars1.length - 1, chars2, chars2.length - 1, aims, aims.length - 1);
	}

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

	public static void main(String[] args) {
		System.out.println(isStringCross("ab", "b12ab", "ab12abb"));
	}
}
