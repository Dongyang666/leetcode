package greedy;

/**
 *
 * @author liudongyang
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 */
public class JumpGame2 {

	/**
	 * 这题为什么能用贪心呢？而不是动规呢？不如下面这个例子
	 * [3,1,10,3,100,1,1,1,1,1,1,1,1,1,1]
	 * 这个例子中第一次从3跳可以选1，10，3 直接选择10
	 * 然后在下标为3-12之间选择，不会错过100这个点
	 * 贪心算法 O(n)
	 * 每次选择都选择最远处
	 * 从前往后算
	 * @param nums
	 * @return
	 */
	public static int jump1(int[] nums) {
		int start = 0;
		int end = 1;
		int ans = 0;
		while (end < nums.length) {
			int canJumpMaxPos = 0;
			for (int i = start; i < end; i++) {
				canJumpMaxPos = Math.max(canJumpMaxPos, nums[i] + i);
			}
			start = end;
			end = canJumpMaxPos + 1;
			ans++;
		}
		return ans;
	}


	/**
	 * dp O(n^2)
	 * 从后往前算
	 * @param nums
	 * @return
	 */
	public static int jump2(int[] nums) {
		int[] dp = new int[nums.length];
		for (int i = nums.length - 2; i >= 0; i--) {
			dp[i] = nums.length + 1;
			for (int j = i; j < nums.length && j < i + nums[i]; j++) {
				dp[i] = Math.min(dp[i], dp[j] + 1);
			}
		}
		return dp[0];
	}

}
