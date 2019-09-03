/**
 * 大数相加
 *
 * @author : dyliu7@iflytek.com
 * @date Date : 2019年02月28日 14:09
 */
public class BigIntegerAdd {
	public static void main(String[] args) {
		//System.out.println(Integer.MAX_VALUE);
		System.out.println(manyAdd("0", "0111", "01234"));
		StringBuilder s = new StringBuilder();
	}

	private static String manyAdd(String... values) {
		if (values.length == 1) {
			return values[0];
		}
		String res = "0";
		for (int i = 0; i < values.length; i++) {
			res = add(res, values[i]);
		}
		return res;
	}

	private static String add(String arg1, String arg2) {
		StringBuilder sb = new StringBuilder();
		int index1 = arg1.length() - 1;
		int index2 = arg2.length() - 1;
		int pre = 0;
		while (index1 >= 0 || index2 >= 0) {
			int num1 = index1 >= 0 ? arg1.charAt(index1--) - '0' : 0;
			int num2 = index2 >= 0 ? arg2.charAt(index2--) - '0' : 0;
			int addNum = num1 + num2 + pre;
			pre = addNum / 10;
			sb.append(addNum % 10);
		}
		return sb.reverse().toString().replaceAll("^0{0," + (sb.length() - 1) + "}", "");
	}
}
