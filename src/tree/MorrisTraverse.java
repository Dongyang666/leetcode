package tree;

import node.TreeNode;

/**
 * missor遍历树 空间复杂度为（1）
 * 三个原则：
 * 1.当前节点没有左子树 直接划到右节点
 * 2.当前节点有左子树，如果左子树最右的节点指向null则代表第一次到当前节点，把左子树最右节点连到当前节点上
 * 3.当前节点有左子树，如果左子树最右的节点指向当前节点则代表第二次到达当前节点，把左子树最右节点置为null
 * 这样游历过的序列叫missor序列
 */
public class MorrisTraverse {
	private static void morrisIn(TreeNode head) {
		if (head == null) {
			return;
		}
		TreeNode cur = head;
		TreeNode leftTreeMostRight = null;
		while (cur != null) {
			leftTreeMostRight = cur.left;
			if (leftTreeMostRight != null) {
				while (leftTreeMostRight.right != null && leftTreeMostRight.right != cur) {
					leftTreeMostRight = leftTreeMostRight.right;
				}
				if (leftTreeMostRight.right == null) {
					leftTreeMostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					leftTreeMostRight.right = null;
				}
			} else {
				System.out.println(cur.val);
				cur = cur.right;
			}
		}
	}
}
