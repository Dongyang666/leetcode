package dp;

/**
 * @author liudongyang
 * 给定一个字符串，请把字符串分割，分割出来的每一个子串，都要求是回文串。 请问最少分割成几部分可以完成这个要求。
 */
public class PalindromeMinCut {

	private static int minCut(String str) {
		return p(str, 0);
	}

	private static int p(String str, int len) {
		if (len == str.length()) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for (int i = len; i < str.length(); i++) {
			boolean subStrResult = isPalindrome(str, len, i);
			if (subStrResult) {
				System.out.println(str.substring(len, i + 1));
				int x = p(str, i + 1);
				res = Math.min(res, x + 1);
				//System.out.println(x + "--" + str.substring(i));
			}

		}
		return res;
	}

	private static boolean isPalindrome(String str, int left, int right) {
		while (left <= right && str.charAt(left++) != str.charAt(right--)) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(minCut("aabb"));
	}
}
