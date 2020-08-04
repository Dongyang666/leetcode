package slidewindow;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 *
 *给定一个数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * @author liudongyang
 */
public class MaxSlidingWindow {
	/**
	 * 使用双端队列，队列的头为当前窗口的最大值。然后扩容窗口的时候维护一下这个双端队列
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] maxSlidingWindow1(int[] nums, int k) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		int len = nums.length > k ? nums.length - k + 1 : 1;
		int[] res = new int[len];
		int left = 0;
		int right = Math.min(k - 1, nums.length - 1);
		int index = 0;
		int cur = 0;
		while (right < nums.length && left <= right) {
			while (cur <= right) {
				if (!deque.isEmpty() && right - deque.getFirst() >= k) {
					deque.removeFirst();
				}
				if (!deque.isEmpty() && nums[deque.getFirst()] < nums[cur]) {
					deque.clear();
				}
				while (!deque.isEmpty() && nums[deque.getLast()] < nums[cur]) {
					deque.removeLast();
				}
				deque.addLast(cur++);
			}
			res[index++] = nums[deque.getFirst()];
			left++;
			right++;

		}
		return res;
	}

	/**
	 * 动态规划
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0) return new int[0];
		if (nums.length == 1) return nums;
		int n = nums.length;
		int[] left = new int[n];
		left[0] = nums[0];
		int[] right = new int[n];
		right[n - 1] = nums[n - 1];
		int[] res = new int[n - k + 1];
		for (int i = 1; i < n; ++i) {
			if (i % k == 0) left[i] = nums[i];
			else left[i] = Math.max(nums[i], left[i - 1]);

			int j = n - 1 - i;
			if ((j + 1) % k == 0) right[j] = nums[j];
			else right[j] = Math.max(nums[j], right[j + 1]);
		}

		for (int i = 0; i < res.length; ++i) {
			res[i] = Math.max(left[i + k - 1], right[i]);
		}
		return res;


	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));

	}
}
