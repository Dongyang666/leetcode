package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p/>
 * Description:
 *
 * @author dyliu7@iflytek.com
 * @date 2020/04/24
 */
public class MergeSort2 {

	public static void sort(int[] arr) {
		int len = arr.length;
		if (len < 2) return;
		//int[] copy = new int[arr.length];
		int[] temp = new int[arr.length];
		mergeSort(arr, 0, len - 1, temp);

	}

	/**
	 * 递归函数
	 * arr[left..right]
	 *
	 * @param arr
	 * @param left
	 * @param right
	 * @param temp
	 */
	private static void mergeSort(int[] arr, int left, int right, int[] temp) {
		//base case
		if (left == right) return;
		int mid = left + ((right - left) >> 1);
		mergeSort(arr, left, mid, temp);
		mergeSort(arr, mid + 1, right, temp);
		merge(arr, left, mid, right, temp);
	}

	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		for (int i = left; i <= right; i++) {
			temp[i] = arr[i];
		}
		int i = left;
		int j = mid + 1;
		for (int k = left; k <= right; k++) {
			if (i == mid + 1) {
				arr[k] = temp[j++];
			} else if (j == right + 1) {
				arr[k] = temp[i++];
			} else if (temp[i] <= temp[j]) {
				arr[k] = temp[i++];
			} else {
				arr[k] = temp[j++];
			}
		}
	}


	public static void main(String[] args) {
		int[] res2 = new int[]{7, 4, 3, 7, 2, 5, 6, 7, 2, 1};
		sort(res2);
		System.out.println(Arrays.toString(res2));
		/*int count = 0;
		while (true) {
			int[] res = getRandomRrray();
			bubleSort(res);
			int[] res2 = Arrays.copyOf(res, res.length);
			sort(res2);
			if (!Arrays.equals(res, res2)) {
				System.out.println(Arrays.toString(res));
				System.out.println(Arrays.toString(res2));
			}
			System.out.println(count++);
		}*/
	}


	public static void bubleSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			boolean flag = true;
			for (int j = 0; j < nums.length - 1 - i; j++) {
				if (nums[j] > nums[j + 1]) {
					flag = false;
					swap(nums, j, j + 1);
				}
			}
			if (flag) {
				break;
			}
		}

	}

	public static int[] getRandomRrray() {
		Random random = new Random();
		int[] ints = new int[random.nextInt(20)];
		for (int i = 0; i < ints.length; i++) {
			int res = random.nextInt(1000);
			ints[i] = (res & 1) == 1 ? res : res * -1;
		}
		return ints;

	}

	private static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
}
