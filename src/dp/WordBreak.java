package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: { 单词拆分}
 * @author: dyliu7@iflytek.com
 * @date: 2019/12/20 15:19
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> strings = new HashSet<>(wordDict);
        List<String> list = new ArrayList<>();
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] && strings.contains(s.substring(j, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> strings = new HashSet<>(wordDict);
        List<String> init = new ArrayList<>();
        List<String>[] dp = new ArrayList[s.length() + 1];
        Map<String, List<String>> map = new HashMap<>();
        init.add("");
        dp[0] = init;
        for (int i = 0; i < s.length(); i++) {
            List<String> list = new ArrayList<>();
            for (int j = i; j >= 0; j--) {
                if (dp[j].size() > 0 && strings.contains(s.substring(j, i + 1))) {
                    for (String item : dp[j]) {
                        list.add(item + (item.equals("") ? "" : " ") + s.substring(j, i + 1));
                        System.out.println(i);
                    }
                }
            }
            dp[i + 1] = list;
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        System.out.println(wordBreak2(s, wordDict));
    }
}
