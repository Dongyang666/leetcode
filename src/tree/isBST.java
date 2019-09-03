package tree;

import node.TreeNode;

public class isBST {
	public boolean morrisIsBST(TreeNode head) {
		if (head == null) {
			return true;
		}
		TreeNode pre = null;
		TreeNode cur = head;
		TreeNode leftTreeMostRight = null;
		while (cur != null) {
			leftTreeMostRight = cur.left;
			if (leftTreeMostRight.left != null) {
				while (leftTreeMostRight.right != null && leftTreeMostRight.right != cur) {
					leftTreeMostRight = leftTreeMostRight.right;
				}
				if (leftTreeMostRight == null) {
					leftTreeMostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					leftTreeMostRight.right = null;
				}
			}
			if (pre != null && pre.val > cur.val) {
				return false;
			}
			pre = cur;
			cur = cur.right;
		}
		return true;
	}
}
