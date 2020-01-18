package array;

import java.util.Arrays;

/**
 * @author liudongyang
 * 俄罗斯套娃问题---实质是最长递增子序列问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AussianDollEnvelopes {
	static class Doll {
		public int h;
		public int w;

		public Doll(int w, int h) {
			this.h = h;
			this.w = w;
		}

	}

	public static int maxEnvelopes(int[][] arr) {
		Doll[] dolls = new Doll[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dolls[i] = new Doll(arr[i][0], arr[i][1]);
		}
		Arrays.sort(dolls, (o1, o2) -> {
			if (o1.w == o2.w) {
				return o2.h - o1.h;
			} else {
				return o1.w - o2.w;
			}
		});
		int[] ends = new int[arr.length];
		ends[0] = dolls[0].h;
		int right = 0;
		for (int i = 1; i < dolls.length; i++) {
			//
			int l = 0;
			int r = right;
			while (l <= r) {
				int m = ((r - l) >> 1) + l;
				if (ends[m] < dolls[i].h) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(l, right);
			ends[l] = dolls[i].h;
		}
		return right + 1;
	}

	public static void main(String[] args) {
		int[][] test = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
		System.out.println(maxEnvelopes(test));
	}
}
