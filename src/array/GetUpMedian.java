package array;

/**
 *
 * @author liudongyang
 *
 * 寻找两个排序数组的上中位数 利用二分加速
 *
 */
public class GetUpMedian {
	public static int getUpMedian(int[] a, int[] b) {
		int s1 = 0;
		int e1 = a.length - 1;
		int s2 = 0;
		int e2 = b.length - 1;
		int mid1 = 0;
		int mid2 = 0;
		int offset = 0;
		while (s1 < e1) {
			mid1 = s1 + (s1 - e1) / 2;
			mid2 = s2 + (s2 - e2) / 2;
			offset = ((e1 - s1 + 1) & 1) ^ 1;
			if (a[mid1] > b[mid2]) {
				e1 = mid1;
				s2 = mid2 + offset;
			} else if (a[mid1] < b[mid2]) {
				s1 = mid1 + offset;
				e2 = mid2;
			} else {
				return a[mid1];
			}
		}
		return Math.min(a[s1], b[s2]);
	}

	public static void main(String[] args) {
		System.out.println(getUpMedian(new int[]{1, 3}, new int[]{2}));
	}
}
