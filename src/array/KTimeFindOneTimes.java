package array;

/**
 * @author liudongyang
 * 在其他数都出现k次的数组中找到只出现一次的数
 * 【题目】 给定一个整型数组arr和一个大于1的整数k。已知arr中只有1个 数出现了1次，其他的数都出现了k次，请返回只出现了1次的数。
 */
public class KTimeFindOneTimes {
	public static int findOneTimes(int[] arr, int k) {
		int[] eO = new int[32];
		for (int i = 0; i < arr.length; i++) {
			setExclusiveOr(arr[i], eO, k);
		}
		return convertKFlagToTen(eO, k);
	}

	public static void setExclusiveOr(int value, int[] eO, int k) {
		int[] curKsysNum = getKSysNumFromNum(value, k);
		for (int i = 0; i < curKsysNum.length; i++) {
			eO[i] = (curKsysNum[i] + eO[i]) % k;
		}
	}

	public static int[] getKSysNumFromNum(int value, int k) {
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

	public static int convertKFlagToTen(int[] arr, int k) {
		int res = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			res = res * k + arr[i];
		}
		return res;
	}

	public static void main(String[] args) {

		// 1 2 4 8
		//10 5 2 1
		//0 1  0 1
		System.out.println(findOneTimes(new int[]{1, 1, 1, 3, 2, 3, 3, 100, 2, 2, 5, 9, 0, 0, 9, 5, 5, 0, 9}, 3));
	}
}
