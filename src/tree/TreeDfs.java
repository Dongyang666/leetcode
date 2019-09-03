package tree;

import node.TreeNode;

import java.util.Stack;

/**
 * @description: { 二叉树的深度遍历递归和非递归版}
 * @author: dyliu7@iflytek.com
 * @date: 2019/05/17 14:03
 */
public class TreeDfs {
	public static void main(String[] args) {
		TreeNode node10 = new TreeNode(10, null, null);
		TreeNode node8 = new TreeNode(8, null, null);
		TreeNode node9 = new TreeNode(9, null, node10);
		TreeNode node4 = new TreeNode(4, null, null);
		TreeNode node5 = new TreeNode(5, node8, node9);
		TreeNode node6 = new TreeNode(6, null, null);
		TreeNode node7 = new TreeNode(7, null, null);
		TreeNode node2 = new TreeNode(2, node4, node5);
		TreeNode node3 = new TreeNode(3, node6, node7);
		TreeNode node1 = new TreeNode(1, node2, node3);
		preOrderNonRecursive(node1);
		long s = Integer.MAX_VALUE + Integer.MAX_VALUE;
		System.out.println(s);
		MorrisTraverse.morrisIn(node1);
	}

	/**
	 * 二叉树后序便利非递归版
	 *
	 * @param root
	 */
	private static void postOrderNonRecursive(TreeNode root) {
		TreeNode curNode;
		TreeNode preNode = null;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			curNode = stack.peek();
			// 从下往上看，如果当前节点没有左右子树可直接访问，如果有左右子树判断子树有没有被访问的方式就是设一个前置节点，如果这个节点
			// 是左右子树其中一个就证明访问过了直接输出当前节点即可，否则表示左右子树未访问，先把右树入栈，再把左树入栈。
			if ((curNode.left == null && curNode.right == null)
					|| (preNode != null && (preNode == curNode.left || preNode == curNode.right))) {
				System.out.print(curNode.val + " ");
				stack.pop();
				preNode = curNode;
			} else {
				if (curNode.right != null) {
					stack.push(curNode.right);
				}
				if (curNode.left != null) {
					stack.push(curNode.left);
				}
			}
		}
	}

	/**
	 * @description: { 二叉树中序遍历非递归版}
	 * @author: dyliu7@iflytek.com
	 * @param: [root]
	 * @return: void
	 * @date: 2019/5/17 14:44
	 */
	private static void inOrderNonRecursive(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			System.out.print(root.val + " ");
			root = root.right;
		}
	}

	/**
	 * @description: {二叉树前序遍历非递归版本 }
	 * @author: dyliu7@iflytek.com
	 * @param: [root]
	 * @return: void
	 * @date: 2019/5/17 14:38
	 */
	private static void preOrderNonRecursive(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			System.out.print(root.val + " ");
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				stack.push(root.left);
			}
		}
	}

	/**
	 * @description: { 递归版前中后序深度遍历二叉树}
	 * @author: dyliu7@iflytek.com
	 * @param: [root]
	 * @return: void
	 * @date: 2019/5/17 14:15
	 */
	private static void recursive(TreeNode root) {
		if (root == null) {
			return;
		}
		// 前序遍历
		// System.out.println(root.val);
		if (root.left != null) {
			recursive(root.left);
		}
		// 中序遍历
		// System.out.println(root.val);
		if (root.right != null) {
			recursive(root.right);
		}
		// 后续遍历
		System.out.print(root.val + " ");
	}
}
