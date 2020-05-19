package tree;

import node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: { }
 * @author: liudongyang
 * @date: 2019/12/13 15:35
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("null");
            } else {
                sb.append(cur.val);
                queue.add(cur.left);
                queue.add(cur.right);
            }
            sb.append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();

    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] split = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = split[index].equals("null") ? null : new TreeNode(Integer.parseInt(split[index]));
            index++;
            cur.right = split[index].equals("null") ? null : new TreeNode(Integer.parseInt(split[index]));
            index++;
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        deserialize("1,2,3,null,null,4,5,null,null,null,null");
    }
}
