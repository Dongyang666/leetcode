/**
 * @description: {kmp算法,实现寻找子串出现的开始位置O（n） }
 * @author: dyliu7@iflytek.com
 * @param:
 * @return:
 * @date: 2019/3/29 9:53
 */
public class Kmp {
  public static void main(String[] args) {
    //

    System.out.println(kmp("abcded", "bc"));
  }

  private static int kmp(String str, String mach) {
    char[] s = str.toCharArray();
    char[] m = mach.toCharArray();
    int[] next = getNext(m);
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
   * @description: { 计算next数组}计算位置上最大前后缀匹配
   * @author: dyliu7@iflytek.com
   * @param: [ch]
   * @return: int[]
   * @date: 2019/4/15 10:47
   */
  private static int[] getNext(char[] ch) {
    int[] next = new int[ch.length];

    /** 最长前后缀匹配定义：不包含当前位置，从0位置和当前位置前一位开始匹配，直到不匹配。例如:abcdecbaf f位置的匹配值为3 */
    /** 初始化0位置直接为-1，表示没有最长前后缀。1位置由于最长前后缀不包含当前位置,直接置为0 */
    int pos = 2;
    next[0] = -1;
    next[1] = 0;
    // 当前位置的
    int cn = 0;
    while (pos < ch.length) {
      if (ch[cn] == ch[pos - 1]) {
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
