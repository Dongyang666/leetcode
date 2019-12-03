package dp;

/**
 * @author liudongyang
 * 最小编辑代价
 * s1 = "1234"
 * s2= "12213"
 * 规定插入 删除 修改的操作价值， 最最小的价值由s1怎么变成s2
 */
public class EditCost {
	public static int minCost(String str1, String str2, int insert, int delete, int replace) {
		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();
		return rec(chars1, chars2, insert, delete, replace, str1.length() - 1, str2.length() - 1);
	}

	public static int rec(char[] ch1, char[] ch2, int insert, int delete, int replace, int len1, int len2) {
		if (len1 == -1) {
			return len2 == -1 ? 0 : len2 * insert;
		}
		if (len2 == -1) {
			return len1 == -1 ? 0 : len1 * delete;
		}
		//编辑代价为正数
		if (ch1[len1] == ch2[len2]) {
			return rec(ch1, ch2, insert, delete, replace, len1 - 1, len2 - 1);
		}
		int left = rec(ch1, ch2, insert, delete, replace, len1, len2 - 1) + delete;
		int up = rec(ch1, ch2, insert, delete, replace, len1 - 1, len2) + insert;
		int leftUpper = rec(ch1, ch2, insert, delete, replace, len1 - 1, len2 - 1) + replace;
		return Math.min(left, Math.min(up, leftUpper));
	}

	public static void main(String[] args) {
		System.out.println(minCost("1234", "1233", 2, 1, 5));
	}

}
