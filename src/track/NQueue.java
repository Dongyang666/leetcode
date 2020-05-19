package track;

/**
 * @author liudongyang
 * N皇后问题
 */
public class NQueue {
	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process(0, record, n);
	}

	private static int process(int i, int[] record, int n) {
		if (i == n) {
			return 1;
		}
		int res = 0;
		for (int k = 0; k < n; k++) {
			if (isValid(record, i, k)) {
				record[i] = k;
				res += process(i + 1, record, n);
			}
		}
		return res;
	}

	/**
	 * @param record
	 * @param i      行
	 * @param k      列
	 * @return
	 */
	private static boolean isValid(int[] record, int i, int k) {
		for (int p = 0; p < i; p++) {
			//对角线和列都不能放
			if (k == record[p] || Math.abs(p - i) == Math.abs(record[p] - k)) {
				return false;
			}
		}
		return true;
	}


	public static int num2(int n) {
		if (n < 1) {
			return 0;
		}
		int upperLim = n == 32 ? -1 : (1 << n) - 1;
		return process1(upperLim, 0, 0, 0);
	}

	private static int process1(int upperLim, int colLim, int leftDiaLim, int rightDaiLim) {
		if (upperLim == colLim) {
			return 1;
		}
		int res = 0;
		//1就是没限制 0是有限制
		int pos = upperLim & (~(colLim | leftDiaLim | rightDaiLim));
		while (pos != 0) {
			//取最右边的1
			int mostRightOne = pos & (~pos + 1);
			pos = pos - mostRightOne;
			res += process1(upperLim, colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDaiLim | mostRightOne) >> 1);

		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(num2(14));

	}

}
