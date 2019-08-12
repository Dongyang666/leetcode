package tree;

import node.TreeNode;

/**
 * 完全二叉树节点数计算
 */
public class CompleteTreeNodeNumber {


	public static int nodeNumber(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	private static int bs(TreeNode head, int level, int h) {
		if (level == h) {
			return 1;
		}
		int s = 1 << (h - level);
		//直接结算当前节点的左子树（满二叉树），并且递归求右子树的节点数
		if (mostLeftLevel(head.right, level + 1) == h) {
			return (1 << (h - level)) + bs(head.right, level + 1, h);
		}
		//结算当前节点的右子树（满二叉树），并且递归求左子树的节点数
		return (1 << (h - level - 1)) + bs(head.left, level + 1, h);

	}

	/**
	 * 以一个节点为头的树的高度
	 *
	 * @param head
	 * @param level
	 * @return
	 */
	private static int mostLeftLevel(TreeNode head, int level) {
		while (head != null) {
			level++;
			head = head.left;
		}
		return level - 1;
	}

}
