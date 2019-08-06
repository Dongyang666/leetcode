import java.util.Arrays;

public class QucikSort {

	/**
	 * @description: { }
	 * @author: dyliu7@iflytek.com
	 * @date: 2019/1/24
	 */
	public static void main(String[] args) {
		int[] a = new int[]{2, 5, 1, 10, 3, 10, 4, 9, 1, 4, 2, 5, 6, 3, 1, 11, 22};
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}

	public static void quickSort(int[] a, int left, int right) {
		if (right > left) {
			int index = partion(a, left, right);
			quickSort(a, left, index - 1);
			quickSort(a, index + 1, right);
		}
	}

	private static int partion(int[] a, int left, int right) {
		dealPivot(a, left, right);
		int i = left;
		int pivot = right - 1;
		int j = right - 1;
		while (true) {
			while (a[++i] < a[pivot]) {
			}
			while (j > left && a[--j] > a[pivot]) {
			}
			if (i < j) {
				swap(a, i, j);
			} else {
				break;
			}
		}
		if (i < right) {
			swap(a, i, right - 1);
		}
		return i;
	}

	private static void dealPivot(int[] a, int left, int right) {
		int midIndex = left + (right - left) / 2;
		if (a[left] > a[midIndex]) {
			swap(a, left, midIndex);
		}
		if (a[left] > a[right]) {
			swap(a, left, right);
		}
		if (a[midIndex] > a[right]) {
			swap(a, midIndex, right);
		}
		swap(a, midIndex, right - 1);
	}

	private static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}
}
