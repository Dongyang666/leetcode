package count;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分类计数问题
 * @author liudongyang
 *给定一个字符串s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 *
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 */
public class CountBinarySubstrings {

	/**
	 * 解题思路
	 * 		把0和1分类计数计算counter数组，然后计算counter数组相邻的两个数取min加起来即可
	 * 	例如00110011
	 * 	counter[2,2,2,2]
	 * 	2 + 2 + 2 = 6
	 * @param s
	 * @return
	 */
	public int countBinarySubstrings(String s) {
		if (s == null || s.length() == 0) return 0;
		List<Integer> counter = new ArrayList<>();
		int ptr = 0;
		while (ptr < s.length()) {
			char c = s.charAt(ptr);
			int count = 0;
			while (ptr < s.length() && s.charAt(ptr) == c) {
				count++;
				ptr++;
			}
			counter.add(count);
		}
		int ans = 0;
		for (int i = 0; i < counter.size() - 1; i++) {
			ans += Math.min(counter.get(i), counter.get(i + 1));
		}
		return ans;
	}
}
