package array;

import java.util.Stack;

/**
 * @author liudongyang
 * 给定一个正数数组arr，arr长度为N，表示有N座山，每座山高度为arr[i]。 请把数组想象成环形数组，相对应的脑补为有N座山包围成一个环。
 * <p>
 * 规定:
 * 1，相邻的两座山，一定能看见。
 * 2，arr[i]和arr[j]的两座山能看见的条件为，在i和j之前，没有任何一座山 arr[k]>Min{arr[i],arr[j]}
 * 输出:有多少对儿，山，可以相互看见。
 */
public class MountainsAndFlames {
	/**
	 * 假如没有相同值，从最小的山峰向两边看总能找见两个值比他大的值（除了最高和次高值）(n-2) * 2对，次高值和最高值相互看见 +1
	 * 结果就是 2n - 3
	 *
	 * @param arr
	 * @return
	 */
	public static int solution1(int[] arr) {
		return arr.length * 2 - 3;
	}

	/**
	 * 假如有相同值，则利用单调栈，先把最高值压入栈中打底，然后按照从大到小的规则进行单调栈，
	 * 有弹出值结算公式为 c(当前弹出值得出现次数，2) + 2 * 出现次数
	 * <p>
	 * 最后stack中剩余如果有两个以上的值，则两个以上的正常结算，
	 * 次高值结算假如说 打底的max为1 则为 c(k,2) + k 大于一则为c(k,2) + k*2
	 * 最高值结算c(k,2)
	 *
	 * @param arr
	 * @return
	 */
	public static int solution2(int[] arr) {
		int resCount = 0;
		Stack<int[]> stack = new Stack<>();
		int start = getLastMaxIndex(arr, stack);
		for (int i = start + 1; i != start; i = i == arr.length - 1 ? 0 : i + 1) {
			int a = arr[i];
			while (a > stack.peek()[0]) {
				int[] pop = stack.pop();
				resCount += cCount(pop[1], 2) + pop[1] * 2;
			}
			if (stack.peek()[0] == a) {
				int[] pop = stack.pop();
				pop[1]++;
				stack.push(pop);
			} else {
				stack.push(new int[]{a, 1});
			}
		}
		while (stack.size() > 2) {
			int[] pop = stack.pop();
			resCount += cCount(pop[1], 2) + pop[1] * 2;
		}
		if (stack.size() == 1) {
			resCount += cCount(stack.pop()[1], 2);
		} else {
			int[] subMax = stack.pop();
			if (stack.peek()[1] > 1) {
				resCount += cCount(subMax[1], 2) + subMax[1] * 2;
			} else {
				resCount += cCount(subMax[1], 2) + subMax[1];
			}
		}
		return resCount;
	}

	private static int cCount(int m, int n) {
		if (m < n) {
			return 0;
		}
		if (m == n) {
			return 1;
		}
		return getFactorial(m) / (getFactorial(m - n) * getFactorial(n));
	}

	private static int getFactorial(int m) {
		if (m == 0) {
			return 0;
		}
		int res = 1;
		for (int i = 1; i <= m; i++) {
			res *= i;
		}
		return res;
	}


	public static void main(String[] args) {

		System.out.println(solution2(new int[]{3, 2, 3, 5, 4}));
	}


	private static int getLastMaxIndex(int[] arr, Stack<int[]> stack) {
		int max = 0;
		int maxIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			int item = arr[i];
			if (item > max) {
				max = item;
				maxIndex = i;
			}
		}
		stack.push(new int[]{max, 1});
		return maxIndex;
	}
}
