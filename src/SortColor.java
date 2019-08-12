/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * @author: dongyangliu
 * @date: 2019/06/05 08:53
 */
public class SortColor {
	public static void sort(int[] nums) {
		//红色的结尾位置（不包含当前位置）
		int p0 = 0;
		//蓝色向前位置
		int p2 = nums.length - 1;
		//遍历到什么位置
		int cur = 0;
		while (cur <= p2) {
			int num = nums[cur];
			if (num == 0) {
				//如果交换当前位置的值，并让红色位置指针和当前位置同时向后移动一位
				//为什么当前位置+1，因为走过的节点肯定是红色所以交换过的节点也是红色可以直接++
				swap(nums, cur++, p0++);
			} else if (num == 2) {
				//当前位置是蓝色，交换当前位置和蓝色位置指针指向的值，当前位置不变，蓝色指针位置向前移动一位
				//为什么当前位置不变，因为之前p2指向的节点没有进行判断是什么颜色
				swap(nums, cur, p2--);
			} else {
				cur++;
			}
		}
	}

	private static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{0, 0};
		sort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}
}
