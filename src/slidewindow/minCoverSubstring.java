package slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liudongyang
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class minCoverSubstring {

	public static String minCoverSubstring(String s, String t) {
		char[] chars = s.toCharArray();
		Map<Character, Integer> needs = new HashMap<>();
		for (char c : t.toCharArray()) {
			needs.put(c, needs.getOrDefault(c, 0) + 1);
		}

		int left = 0;
		int right = 0;
		String ans = "";
		// 用于记录当前窗口中有效的字符的个数
		int validCountInWindow = 0;
		Map<Character, Integer> window = new HashMap<>();
		while (right < chars.length) {
			char rightChar = chars[right++];
			if (needs.containsKey(rightChar)) {
				window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
				if (window.get(rightChar).equals(needs.get(rightChar))) {
					validCountInWindow++;
				}
			}

			//如果有效的个数等于需要的长度 更新
			while (validCountInWindow == needs.size()) {
				// 结算一次
				if (ans.equals("") || right - left < ans.length()) {
					ans = s.substring(left, right);
				}
				char leftChar = chars[left++];
				if (needs.containsKey(leftChar)) {
					// 当前窗口中要移出的字符的个数和这个字符需要的个数相同的话，窗口的有效值就需要减一
					if (window.get(leftChar).equals(needs.get(leftChar))) {
						validCountInWindow--;
					}
					window.put(leftChar, window.get(leftChar) - 1);
				}
			}
		}
		return ans;
	}


	public static String minCoverSubstring2(String s, String t) {
		char[] chars = s.toCharArray();
		Map<Character, Integer> needs = new HashMap<>();
		for (char c : t.toCharArray()) {
			needs.put(c, needs.getOrDefault(c, 0) + 1);
		}

		int left = 0;
		int right = 0;
		String ans = "";
		// 用于记录当前窗口中有效的字符的个数
		int validCountInWindow = 0;
		Map<Character, Integer> window = new HashMap<>();
		while (right < chars.length) {
			char rightChar = chars[right++];
			if (needs.containsKey(rightChar)) {
				window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
				if (window.get(rightChar).equals(needs.get(rightChar))) validCountInWindow++;
			}

			//如果有效的个数等于需要的长度 更新
			while (validCountInWindow == needs.size()) {
				// 结算一次
				if (ans.equals("") || right - left < ans.length()) {
					ans = s.substring(left, right);
				}
				char leftChar = chars[left++];
				if (needs.containsKey(leftChar)) {
					// 当前窗口中要移出的字符的个数和这个字符需要的个数相同的话，窗口的有效值就需要减一
					window.put(leftChar, window.get(leftChar) - 1);
					//if (window.get(leftChar) == 0) window.remove(leftChar);
					if (!needs.get(leftChar).equals(window.get(leftChar))) validCountInWindow--;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(minCoverSubstring2("ADOBECODEBANC", "ABC"));
	}
}
