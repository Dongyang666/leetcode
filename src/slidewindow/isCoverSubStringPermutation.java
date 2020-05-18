package slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liudongyang
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 */
public class isCoverSubStringPermutation {
	public boolean checkInclusion(String s1, String s2) {
		int[] needs = new int[26];
		int validLen = 0;
		for (Character c : s1.toCharArray()) {
			if (needs[c - 'a'] == 0) validLen++;
			needs[c - 'a']++;
		}
		int[] window = new int[26];
		int left = 0;
		int right = 0;
		int valid = 0;
		char[] chars = s2.toCharArray();
		while (right < s2.length()) {
			char rightChar = chars[right++];
			int rightIndex = rightChar - 'a';
			if (needs[rightIndex] > 0) {
				window[rightIndex]++;
				if (needs[rightIndex] == window[rightIndex]) {
					valid++;
				}
			}
			// 维持一个s1大小的窗口
			while (right - left >= s1.length()) {
				if (valid == validLen) return true;
				char leftChar = chars[left];
				int leftIndex = leftChar - 'a';
				left++;
				if (needs[leftIndex] > 0) {
					if (needs[leftIndex] == window[leftIndex]) {
						valid--;
					}
					window[leftIndex]--;
				}
			}
		}
		return false;
	}


	public static boolean checkInclusion1(String s1, String s2) {
		Map<Character, Integer> needs = new HashMap<>();
		for (Character c : s1.toCharArray()) {
			needs.put(c, needs.getOrDefault(c, 0) + 1);
		}
		Map<Character, Integer> window = new HashMap<>();
		int left = 0;
		int right = 0;
		int valid = 0;
		char[] chars = s2.toCharArray();
		while (right < s2.length()) {
			char rightChar = chars[right++];
			if (needs.containsKey(rightChar)) {
				window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
				if (window.get(rightChar).equals(needs.get(rightChar))) {
					valid++;
				}
			}
			// 维持一个s1大小的窗口
			while (right - left >= s1.length()) {
				if (valid == needs.size()) return true;
				char leftChar = chars[left];
				left++;
				if (needs.containsKey(leftChar)) {
					if (window.get(leftChar).equals(needs.get(leftChar))) {
						valid--;
					}
					window.put(leftChar, window.get(leftChar) - 1);
				}
			}
		}
		return false;
	}
}
