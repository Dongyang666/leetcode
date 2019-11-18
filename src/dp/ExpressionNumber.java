package dp;

/**
 * 表达式得到期望结果数的种数
 */
public class ExpressionNumber {

    public static int num1(String express, boolean desired) {
        return way1(express.toCharArray(), desired, 0, express.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(num1("1^0|0|1", false));
    }

    public static int way1(char[] express, boolean desired, int L, int R) {
        //base case
        if (L == R) {
            if (express[L] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        //当前位置怎么计算
        // & | ^
        if (desired) {
            //true
            for (int i = L + 1; i < R; i += 2) {
                //遍历每一个符号的位置
                switch (express[i]) {
                    case '&':
                        res += way1(express, true, L, i - 1) * way1(express, true, i + 1, R);
                        break;
                    case '|':
                        res += way1(express, true, L, i - 1) * way1(express, true, i + 1, R);
                        res += way1(express, true, L, i - 1) * way1(express, false, i + 1, R);
                        res += way1(express, false, L, i - 1) * way1(express, true, i + 1, R);
                        break;
                    case '^':
                        res += way1(express, true, L, i - 1) * way1(express, false, i + 1, R);
                        res += way1(express, false, L, i - 1) * way1(express, true, i + 1, R);
                        break;
                    default:
                        break;
                }
            }
            //false
        } else {
            for (int i = L + 1; i < R; i += 2) {
                //遍历每一个符号的位置
                switch (express[i]) {
                    case '&':
                        res += way1(express, false, L, i - 1) * way1(express, false, i + 1, R);
                        res += way1(express, true, L, i - 1) * way1(express, false, i + 1, R);
                        res += way1(express, false, L, i - 1) * way1(express, true, i + 1, R);
                        break;
                    case '|':
                        res += way1(express, false, L, i - 1) * way1(express, false, i + 1, R);
                        break;
                    case '^':
                        res += way1(express, true, L, i - 1) * way1(express, true, i + 1, R);
                        res += way1(express, false, L, i - 1) * way1(express, false, i + 1, R);
                        break;
                    default:
                        break;
                }
            }
        }
        return res;
    }
}
