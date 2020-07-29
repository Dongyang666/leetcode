package string;

/**
 * @author liudongyang
 * @date 2020/06/22
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），
 * 该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。
 * 编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 *
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 *
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 *
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 *
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 *
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 *
 * 枚举a的长度 然后计算出来b的长度
 * 遍历整个pattern字符串遇到a就通过长度截取出来a。遇到b就截取出来b
 * 如果a出现a不相同直接跳出当前a的长度枚举下一个，或者b不相同就跳出a的长度枚举下一个
 */
public class PatternMatching {
    public static boolean patternMatching(String pattern, String value) {
        int countA = 0, countB = 0;
        for (Character ch : pattern.toCharArray()) {
            if (ch == 'a') countA++;
            else countB++;
        }

        if (countA < countB) {
            int temp = countA;
            countA = countB;
            countB = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }

        if (value.length() == 0) {
            return countB == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        /**
         * 枚举A
         */
        for (int len_a = 0; countA * len_a <= value.length(); ++len_a) {
            // 所有B的长度
            int rest = value.length() - countA * len_a;
            // 如果剩下的长度不能被b均分 直接跳过
            if ((countB == 0 && rest == 0) || (countB != 0 && rest % countB == 0)) {
                int len_b = (countB == 0 ? 0 : rest / countB);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch : pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;

    }
}
