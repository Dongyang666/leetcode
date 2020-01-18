package sort;

import java.util.Arrays;

import static sort.QuickSort.getRandomRrray;

/**
 * @author liudongyang
 * 归并排序
 */
public class MergeSort {
	public static void sort(int[] arr) {
		if (arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left == right) {
			return;
		}
		int mid = ((right - left) >> 1) + left;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		int helpIndex = 0;
		int leftIndex = left;
		int rightIndex = mid + 1;
		while (leftIndex <= mid && rightIndex <= right) {
			help[helpIndex++] = arr[leftIndex] > arr[rightIndex] ? arr[rightIndex++] : arr[leftIndex++];
		}
		while (leftIndex <= mid) {
			help[helpIndex++] = arr[leftIndex++];
		}
		while (rightIndex <= right) {
			help[helpIndex++] = arr[rightIndex++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
	}

	public static void main(String[] args) {
		int count = 0;
		while (true) {
			int[] res = getRandomRrray();
			QuickSort.bubleSort(res);
			int[] res2 = Arrays.copyOf(res, res.length);
			sort(res2);
			if (!Arrays.equals(res, res2)) {
				System.out.println(Arrays.toString(res));
				System.out.println(Arrays.toString(res2));
			}
			//System.out.println(count++);
		}
	}

}
