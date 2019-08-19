package tree;

import node.TreeNode;

/**
 * missor遍历树 空间复杂度为（1）
 * ˘ * 后序遍历，第二次到达是逆序打印左子树的右边界
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
					//先序遍历（有左子树来两次这个节点，第一次打印就是先序遍历，第二次来打印就是中序遍历）
					System.out.println(cur.val);
					leftTreeMostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					//中序遍历
					System.out.println(cur.val);
					leftTreeMostRight.right = null;
				}
			}
			//先序遍历
			else {
				System.out.println(cur.val);
			}
			//中序遍历
			System.out.println(cur.val);
			cur = cur.right;

		}
	}
}
