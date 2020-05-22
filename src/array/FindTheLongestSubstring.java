package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1371. 每个元音包含偶数次的最长子字符串
 *
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 *
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 *
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *
 * @author liudongyang
 * @date 2020/05/20
 */
public class FindTheLongestSubstring {
    /**
     * 思路：题目中要求字符串中每一个元音字母恰好出现偶数次，我们可以使用0和1来标识每一个字母
     *
     * 那么我们可以注意到奇数次 + 1 = 偶数次，偶数次 + 1 = 奇数次，所以我们可以使用 异或 来参与运算： 比如 aba
     *
     * 初始时 status = 00000，然后到 a 的时候 00000 ^ 00001 = 00001，1 说明 a 出现奇数次
     *
     * 然后到 b 的时候 00001 ^ 00010 = 00011，两个 1 说明 a、b 都出现奇数次
     *
     * 最后到 a 的时候 00011 ^ 00001 = 00010，说明只有 b 出现奇数次了。
     *
     * @param s
     * @return
     */
    public int findTheLongestSubstring(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int status = 0;
        int ans = 0;
        // status == 0 默认 -1
        map.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'a':
                    status ^= 1 << 0;
                    break;
                case 'e':
                    status ^= 1 << 2;
                    break;
                case 'i':
                    status ^= 1 << 3;
                    break;
                case 'o':
                    status ^= 1 << 4;
                    break;
                case 'u':
                    status ^= 1 << 5;
                    break;
            }
            if (map.containsKey(status)) {
                ans = Math.max(ans, i - map.get(status));
            }
            map.putIfAbsent(status, i);
        }

        return ans;

    }
}
