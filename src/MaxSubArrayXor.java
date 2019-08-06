/**
 * @description: { 求子数组的最大异或值---前缀树实现 o(n)}
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/7/9 20:23
 */
public class MaxSubArrayXor {

	private Node root = new Node();

	public int maxXorSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
          return 0;
        }
		int max = Integer.MIN_VALUE;
		int eor = 0;
		MaxSubArrayXor maxSubArrayXor = new MaxSubArrayXor();
		maxSubArrayXor.insert(0);
		for (int i = 0; i < nums.length; i++) {
			//
			eor ^= nums[i];
			max = Math.max(max, maxSubArrayXor.maxXor(eor));
			maxSubArrayXor.insert(eor);
		}
		return max;
	}

	/**
	 * 前缀树插入节点
	 *
	 * @param num
	 */
	public void insert(int num) {
		Node cur = root;
		for (int i = 31; i >= 0; i--) {
			// 按位进行判断。
			int path = (num >> i) & 1;
			cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
			cur = cur.nexts[path];
		}
	}

	/**
	 * 计算当前值可以取得的最大的异或值对象
	 *
	 * @param num
	 * @return
	 */
	public int maxXor(int num) {
		Node cur = root;
		int res = 0;
		for (int move = 31; move >= 0; move--) {
			int path = (num >> move) & 1;
			// best是选择异或最好的对象
			int best = move == 31 ? path : path ^ 1;
			best = cur.nexts[best] != null ? best : best ^ 1;
			res |= (best ^ path) << move;
			cur = cur.nexts[best];
		}
		return res;
	}

	public class Node {
		Node[] nexts = new Node[2];
	}
}
