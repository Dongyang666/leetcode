package tree;

import node.TreeNode;

/**
 * missor遍历树 空间复杂度为（1）
 * ˘ * 后序遍历，第二次到达是逆序打印左子树的右边界
 */
public class MorrisTraverse {
	public static void morrisIn(TreeNode head) {
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
					System.out.print(cur.val + " ");
					leftTreeMostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					leftTreeMostRight.right = null;
				}
			}
			//先序遍历
			else {
				System.out.print(cur.val + " ");
			}
			//右滑之前打印即为中序遍历
			//System.out.print(cur.val + " ");
			cur = cur.right;

		}
	}

	public static void morrisPost(TreeNode head) {
		if (head == null) {
			return;
		}
		TreeNode cur = head;
		//左子树最右节点
		TreeNode leftTreeMostRight = null;
		while (cur != null) {
			leftTreeMostRight = cur.left;
			if (leftTreeMostRight != null) {
				while (leftTreeMostRight.right != null && leftTreeMostRight.right != cur) {
					leftTreeMostRight = leftTreeMostRight.right;
				}
				//第一次到达
				if (leftTreeMostRight.right == null) {
					leftTreeMostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					leftTreeMostRight.right = null;
					//第二次到达
					printNode(cur.left);
				}

			}
			//第二次到达和左节点为空都需要向右滑动
			cur = cur.right;

		}
		printNode(head);
	}

	private static void printNode(TreeNode head) {
		TreeNode node = reverseEdge(head);
		TreeNode tail = node;
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.right;
		}
		reverseEdge(tail);
	}

	private static TreeNode reverseEdge(TreeNode from) {
		TreeNode pre = null;
		TreeNode next;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}
}
