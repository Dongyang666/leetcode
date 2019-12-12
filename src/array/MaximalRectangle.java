package array;

import java.util.Stack;

/**
 * @author liudongyang
 * 给定一个矩阵，其中的值只有0和1，求内部全是1的最大的子矩 阵，返回该矩阵中有多少个1即可。
 * 二维数组压缩问题
 */
public class MaximalRectangle {
	public static int maxRecSize(int[][] arr) {
		int max = 0;
		//压缩数组
		int[] tmp = new int[arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				int x = 0;
				if (arr[i][j] != 0) {
					x = tmp[j] + arr[i][j];
				}
				tmp[j] = x;
			}
			max = Math.max(max, getMaxRectangle(tmp));
		}
		return max;
	}

	/**
	 * 算法原型：给一个一维数组，数组中的每个值代表小正方形的个数，求这个数组中能连在一起的最大的小正方形个数
	 * [1,2,4,5,6,4,5]
	 * 一个格子，两个格子，四个格子，五个格子，6个格子，4个格子，5个格子
	 * 其实就是每一个位置求左边离他最近比它小的值的位置，和右边离他最近比他小的值。。。。
	 * 从小到大的单调栈能很好地解决这个问题。但由于有重复的数字 会破坏单调栈，（遇到相同的就当是遇到小于他的值处理），
	 * 这样不会错过最终答案。（相同值得最后一个值能正确结算）
	 */
	public static int getMaxRectangle(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int cur = arr[i];
			while (!stack.isEmpty() && arr[stack.peek()] >= cur) {
				int pop = stack.pop();
				int leftIndex = stack.isEmpty() ? -1 : stack.peek();
				int rightIndex = i;
				max = Math.max(max, arr[pop] * (rightIndex - leftIndex - 1 == 0 ? 1 : rightIndex - leftIndex - 1));
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int pop = stack.pop();
			int leftIndex = stack.isEmpty() ? -1 : stack.peek();
			max = Math.max(max, arr[pop] * (arr.length - leftIndex - 1));
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
		System.out.println(maxRecSize(map));
	}
}
