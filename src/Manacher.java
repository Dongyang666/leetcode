/**
 * @description: { 马拉车算法寻找最长回文串O（n）}
 * @author: dyliu7@iflytek.com
 * @date: 2019/3/29 9:50
 */
public class Manacher {
	public static void main(String[] args) {
		//
		System.out.println(manacher("bvbvvbcc"));
	}

	private static String manacher(String s) {
		char[] str = getStr(s);
		int[] pArr = new int[str.length];
		// 能扩到最右边的位置
		int pR = -1;
		// 记录最长回文串的索引值
		int max_index = 0;
		// 能扩到最右边位置的索引值
		int index = -1;
		// 记录最长回文串长度
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < str.length; i++) {
			// 如果当前位置已经超过可以扩到的最右位置，目前可知的最大半径只为1，不然置为关于index位置的对称位置可扩的最大位置值和当前位置距离最右位置中较小的那一个
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			// 开始进行原始匹配左右++进行扩。如果不相同证明当前位置已经扩到了直接break
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			// 如果当前位置比之前pR扩的远，更新pR和index
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			// 如果max小于当前位置可以扩到的最远距离，更新max并记录max的index位置
			if (max < pArr[i]) {
				max = pArr[i];
				max_index = i;
			}
		}
		// 截取最长回文串
		return new String(str, max_index - (max - 1), (max - 1) * 2 + 1).replaceAll("#", "");
	}

	/**
	 * @description: { 字符串添加分隔符号，方便操作}
	 * @author: dyliu7@iflytek.com
	 * @param: [str]
	 * @return: char[]
	 * @date: 2019/4/15 15:54
	 */
	private static char[] getStr(String str) {
		char[] chars = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			// 与1运算，判断当前位置为偶数为特殊字符，奇数为数字。
			chars[i] = (i & 1) == 1 ? str.charAt(index++) : '#';
		}
		return chars;
	}
}
