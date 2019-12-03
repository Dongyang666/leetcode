package tree;

import node.TreeNode;

/**
 * @description: { 判断是不是对称二叉树}
 * @author: dyliu7@iflytek.com
 * @date: 2019/11/22 10:14
 */
public class SymmetricTree {

    public static boolean isSymmetric(TreeNode head) {
        if (head == null) {
            return true;
        }
        return recursive(head.left, head.right);
    }

    /**
     * 二叉树套路
     *
     * @param left
     * @param right
     * @return
     */
    private static boolean recursive(TreeNode left, TreeNode right) {
        //base case
        if (left == null) {
            return right == null;
        }
        if (left.val != right.val) {
            return false;
        }
        //收集信息
        boolean isLeftSymmetric = recursive(left.right, right.left);
        boolean isRightSymmetric = recursive(left.left, right.right);
        //组合信息并返回
        return isLeftSymmetric && isRightSymmetric;
    }
}
