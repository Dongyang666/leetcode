package track;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liudongyang
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class Permute {
	private static final List<List<Integer>> res = new ArrayList<>();

	public static List<List<Integer>> permute(int[] arr) {
		LinkedList<Integer> list = new LinkedList<>();
		backtrack(arr, list);
		return res;
	}

	/**
	 * 回溯算法的模板递归函数如下
	 *
	 * {
	 *     //1.base case
	 *     if(递归终止条件){
	 *         收集最终的结果信息
	 *         return;
	 *     }
	 *     for(遍历每一个路径){
	 *         //前序遍历到要干什么事情
	 *         backtrack递归
	 *         //后序遍历到要干什么事情
	 *     }
	 * }
	 * @param arr
	 * @param list
	 */

	private static void backtrack(int[] arr, LinkedList<Integer> list) {
		//base case
		if (arr.length == list.size()) {
			res.add(new ArrayList<>(list));
			//return;
		}
		for (int value : arr) {
			if (list.contains(value)) {
				continue;
			}
			//前序
			list.add(value);
			backtrack(arr, list);
			list.removeLast();
		}
	}


}
