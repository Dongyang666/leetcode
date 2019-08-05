/**
 * @author : dyliu7@iflytek.com
 * @Project: leetcode
 * @Package PACKAGE_NAME
 * @Description: TODO
 * @date Date : 2019年02月28日 14:09
 */
public class BigIntegerAdd {
    public static void main(String[] args) {
        System.out.println(add("000000000000183", "000012812121"));
        StringBuilder s = new StringBuilder();
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
        return sb.reverse().toString().replaceAll("^0*", "");
    }
}
