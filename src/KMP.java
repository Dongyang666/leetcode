import java.util.Arrays;
import java.util.Scanner;

/**
 * @author liudongyang
 * @date 2020/07/09
 */
public class KMP {

    public static int[] getNext(String match) {
        char[] matchChars = match.toCharArray();
        int[] next = new int[match.length()];
        next[0] = -1;
        for (int i = 2; i < next.length; i++) {
            if (matchChars[next[i - 1]] == matchChars[i - 1]) {
                next[i] = Math.max(next[i - 1] + 1, 1);
            } else {
                int cnt = next[i - 1];
                while (cnt >= 0 && matchChars[cnt] != matchChars[i - 1]) {
                    cnt = next[cnt];
                }
                if (cnt != -1) {
                    next[i] = Math.max(next[cnt] + 1, 1);
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            int sum = 0;
            int max = 0;
            for (int i = 0; i < n; ++i) {
                sum = Math.max(sum, 0);
                sum += sc.nextInt();
                max = Math.max(max, sum);
            }
            System.out.print(max);
        }

    }

    public static int matchCount(String str, String match) {
        int[] next = getNext(match);
        char[] chars = str.toCharArray();
        char[] mChars = match.toCharArray();
        System.out.println(Arrays.toString(next));
        int sc = 0;
        int mc = 0;
        while (sc < str.length() && mc < match.length()) {
            if (chars[sc] == mChars[mc]) {
                sc++;
                mc++;
            } else if (next[mc] == -1) {
                sc++;
            } else {
                mc = next[mc];
            }
        }
        return mc == match.length() ? sc - mc : -1;
    }

}
