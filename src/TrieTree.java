/**
 * @description: { 前缀树}
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/7/8 15:37
 */
public class TrieTree {
	private Node root;

	public class Node {
		/** 后续字符先new出来 */
		Node[] map = new Node[26];
		/** 结尾次数 */
		int end;
		/** 划过次数 */
		int path;
	}

	public TrieTree() {
		root = new Node();
	}

	public void insert(String str) {

		if (str == null) {
			return;
		}
		char[] chars = str.toCharArray();
		Node node = root;
		int index;
		for (char ch : chars) {
			index = ch - 'a';
			if (node.map[index] == null) {
				node.map[index] = new Node();
			}
			node = node.map[index];
			node.path++;
		}
		node.end++;
	}

	public void delete(String str) {
		if (!search(str)) {
			return;
		}
		char[] chars = str.toCharArray();
		int index;
		Node node = root;
		for (char ch : chars) {
			index = ch - 'a';
			if (node.map[index].path-- == 1) {
				node.map[index] = null;
				return;
			}
			node = node.map[index];
		}
		node.end--;
	}

	public boolean search(String str) {
		if (str == null) {
			return false;
		}
		char[] chars = str.toCharArray();
		Node node = root;
		int index;
		for (char ch : chars) {
			index = ch - 'a';
			if (node.map[index] != null && node.map[index].path > 0) {
				node = node.map[index];
			}
		}
		return node.end > 0;
	}

	public boolean searchPrefix(String prefix) {
		if (prefix == null) {
			return false;
		}
		char[] chars = prefix.toCharArray();
		Node node = root;
		int index;
		for (char ch : chars) {
			index = ch - 'a';
			if (node.map[index] != null && node.map[index].path > 0) {
				node = node.map[index];
			}
		}
		return true;
	}
}
