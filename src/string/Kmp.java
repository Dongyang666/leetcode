package string;

import java.util.Arrays;

/**
 * @description: {kmp算法,实现寻找子串出现的开始位置O（n） }
 * @author: liudongyang
 * @date: 2019/3/29 9:53
 */
public class Kmp {
    public static void main(String[] args) {
        //

        System.out.println(kmp("hello", "abc1abc1"));
    }

    private static int kmp(String str, String mach) {
        char[] s = str.toCharArray();
        char[] m = mach.toCharArray();
        int[] next = getNext(m);
        System.out.println(Arrays.toString(next));
        int si = 0;
        int mi = 0;
        while (si < s.length && mi < m.length) {
            if (s[si] == m[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == m.length ? si - mi : -1;
    }

    /**
     * 计算位置上最大前后缀匹配
     */
    private static int[] getNext(char[] mach) {
        int[] next = new int[mach.length];

        // 最长前后缀匹配定义：不包含当前位置，从0位置和当前位置前一位开始匹配，直到不匹配。例如:abcdecbaf f位置的匹配值为3
        // 初始化0位置直接为-1，表示没有最长前后缀。1位置由于最长前后缀不包含当前位置,直接置为0
        int pos = 2;
        next[0] = -1;
        next[1] = 0;
        // 当前位置的
        int cn = 0;
        while (pos < mach.length) {
            if (mach[cn] == mach[pos - 1]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
