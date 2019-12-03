package dp;

/**
 * @author liudongyang
 * 最长公共子序列
 */
public class LongestCommonSubsequence {

	public static int process(String str1, String str2) {
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		String commonString = recGetString(ch1, ch1.length - 1, ch2, ch2.length - 1);
		int commonLength = recGetLength(ch1, ch1.length - 1, ch2, ch2.length - 1);
		System.out.println(commonString);
		return commonLength;
	}

	/**
	 * 递归求最长公共子序列字符串
	 *
	 * @param ch1
	 * @param length1 需要求的子串1的长度
	 * @param ch2
	 * @param length2 需要求的子串2的长度
	 * @return
	 */
	private static String recGetString(char[] ch1, int length1, char[] ch2, int length2) {
		if (length1 < 0 || length2 < 0) {
			return "";
		}
		if (ch1[length1] == ch2[length2]) {
			//加到前后不一样
			return recGetString(ch1, length1 - 1, ch2, length2 - 1) + ch1[length1];
		}
		String rec1 = recGetString(ch1, length1, ch2, length2 - 1);
		String rec2 = recGetString(ch1, length1 - 1, ch2, length2);
		return rec1.length() > rec2.length() ? rec1 : rec2;


	}

	/**
	 * 递归求最长公共子序列长度
	 *
	 * @param ch1
	 * @param len1
	 * @param ch2
	 * @param len2
	 * @return
	 */
	private static int recGetLength(char[] ch1, int len1, char[] ch2, int len2) {
		if (len1 < 0 || len2 < 0) {
			return 0;
		}
		if (ch1[len1] == ch2[len2]) {
			return recGetLength(ch1, len1 - 1, ch2, len2 - 1) + 1;
		}
		return Math.max(
				recGetLength(ch1, len1 - 1, ch2, len2),
				recGetLength(ch1, len1, ch2, len2 - 1));
	}


	public static void main(String[] args) {
		process("1234abc", "11234a55");
	}

}
