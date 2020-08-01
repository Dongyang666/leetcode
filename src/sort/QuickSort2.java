package sort;

import java.util.Random;

/**
 *
 * @author liudongyang
 */
public class QuickSort2 {
	public static void sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int p = part2(arr, left, right);
			//int p = part(arr, left, right);
			quickSort(arr, left, p - 1);
			quickSort(arr, p + 1, right);
		}

	}

	private static int part(int[] arr, int left, int right) {
		int p = new Random().nextInt(right - left + 1) + left;
		swap(arr, p, left);
		int j = left;
		for (int i = left + 1; i <= right; ++i) {
			if (arr[i] <= arr[left]) {
				j++;
				swap(arr, i, j);
			}
		}
		swap(arr, left, j);
		return j;
	}

	private static int part1(int[] arr, int left, int right) {
		int p = new Random().nextInt(right - left + 1) + left;
		swap(arr, p, right);
		int j = left;
		for (int i = left; i < right; ++i) {
			if (arr[i] <= arr[right]) {
				swap(arr, i, j);
				j++;
			}
		}
		swap(arr, right, j);
		return j;
	}

	private static int part2(int[] arr, int left, int right) {
		int p = new Random().nextInt(right - left + 1) + left;
		swap(arr, p, left);
		int pivot = arr[left];
		int lt = left + 1;
		int gt = right;
		for (; ; ) {
			while (lt < right && arr[lt] < pivot) {
				lt++;
			}
			while (gt > left && arr[gt] > pivot) {
				gt--;
			}
			if (lt > gt) {
				break;
			}
			// 到这地方是需要lt和gt进行交换
			swap(arr, lt++, gt--);
		}
		// 到这个地方的gt一定是小于pivot
		swap(arr, left, gt);
		return gt;
	}

	public static void swap(int[] arr, int p, int q) {
		int tmp = arr[p];
		arr[p] = arr[q];
		arr[q] = tmp;
	}


}
