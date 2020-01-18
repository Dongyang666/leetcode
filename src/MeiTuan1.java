import java.util.Scanner;

/**
 * @author liudongyang
 * 给你一个01字符串，定义答案=该串中最长的连续1的长度，现在你有至多K次机会，每次机会可以将串中的某个0改成1，现在问最大的可能答案
 */
public class MeiTuan1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		int k = sc.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int l = 0, r = 0;
		int max = 0;
		while (r < arr.length) {
			if (arr[r] == 1) {
			} else if (k > 0) {
				k--;
			} else {
				max = Math.max(max, r - l);
				while (arr[l] != 0) {
					l++;
				}
				l++;
			}
			r++;
		}
		max = Math.max(max, r - l);
		System.out.println(max);
	}
}
