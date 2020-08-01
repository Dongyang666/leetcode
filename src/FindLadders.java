import java.util.*;

/**
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 *
 * 解释:endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 * @author liudongyang
 */
public class FindLadders {
	static List<List<String>> res = new ArrayList<>();

	public static void main(String[] args) {
		findLadders("hit", "cog", new ArrayList<String>() {{
			add("hot");
			add("dot");
			add("dog");
			add("lot");
			add("log");
			add("cog");
		}});
		System.out.println(getB(8));
	}

	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord)) return res;
		bfs(beginWord, endWord, new HashSet<String>(wordList), res);
		return res;

	}

	public static String getB(int k) {
		StringBuilder res = new StringBuilder();
		for (int i = 30; i > -1; --i) {
			if (k >= (1 << i)) {
				k -= (1 << i);
				res.append(1);
			} else {
				res.append(0);
			}

		}
		return res.toString();
	}


	public static void bfs(String beginWord, String endWord, Set<String> wordList, List<List<String>> ans) {
		Queue<List<String>> queue = new LinkedList<>();
		List<String> path = new ArrayList<>();
		path.add(beginWord);
		queue.offer(path);
		Set<String> isV = new HashSet<>();
		isV.add(beginWord);
		boolean flag = false;
		while (!queue.isEmpty()) {
			int size = queue.size();
			Set<String> subV = new HashSet<>();
			for (int i = 0; i < size; ++i) {
				System.out.println(queue);
				List<String> curList = queue.poll();
				String last = curList.get(curList.size() - 1);
				Set<String> ners = getNext(last, wordList);
				for (String ner : ners) {
					if (!isV.contains(ner)) {
						curList.add(ner);
						if (ner.equals(endWord)) {
							flag = true;
							ans.add(new ArrayList<>(curList));
						} else {
							subV.add(ner);
						}
						queue.offer(new ArrayList<>(curList));
						curList.remove(curList.size() - 1);
					}
				}
				isV.addAll(subV);
				if (flag) break;
			}
		}
	}


	public static Set<String> getNext(String curS, Set<String> wordList) {
		Set<String> list = new HashSet<>();
		char[] chars = curS.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char ch = chars[i];
			for (int j = 0; j < 26; ++j) {
				if (j + 'a' == ch) continue;
				chars[i] = (char) (j + 'a');
				if (wordList.contains(new String(chars))) {
					list.add(new String(chars));
				}
			}
			chars[i] = ch;
		}
		return list;
	}
}
