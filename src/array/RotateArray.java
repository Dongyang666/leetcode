package array;

/**
 * @author liudongyang
 * 旋转数组
 * 二分
 */
public class RotateArray {

	public static int findRotateIndex(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		if (arr[left] <= arr[right]) {
			return 0;
		}
		while (left <= right) {
			int mid = (right - left >> 1) + left;
			if (arr[mid] > arr[mid + 1]) {
				return mid;
			}
			if (arr[mid] < arr[left]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return 0;
	}

	public static int binarySearch(int[] arr, int left, int right, int target) {
		while (left <= right) {
			int mid = (right - left >> 1) + left;
			if (arr[mid] == target) {
				return mid;
			}
			if (arr[mid] > target) {
				right = mid - 1;
			} else if (arr[mid] < target) {
				left = mid + 1;
			}
		}
		return -1;
	}

}
