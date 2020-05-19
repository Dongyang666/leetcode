package mcase;

import java.util.Scanner;

/**
 * @author liudongyang
 * 贪心
 * <p>
 * 公园里有N个花园，初始时每个花园里都没有种花，园丁将花园从1到N编号并计划在编号为i的花园里恰好种A_i朵花，他每天会选择一个区间[L，R]（1≤L≤R≤N）并在编号为L到R的花园里各种一朵花，那么园丁至少要花多少天才能完成计划？
 * <p>
 * <p>
 * 输入描述:
 * 第一行包含一个整数N，1≤N≤10^5。
 * <p>
 * 第二行包含N个空格隔开的整数A_1到A_N，0≤A_i≤10^4。
 * <p>
 * 输出描述:
 * 输出完成计划所需的最少天数。
 * <p>
 * 输入例子1:
 * 5
 * 4 1 8 2 5
 * <p>
 * 输出例子1:
 * 14
 */
public class MeiTuan3 {
	public static int process(int[] arr, int left, int right) {
		if (left > right) {
			return 0;
		}
		if (left == right) {
			return arr[left];
		}
		int pre = left;
		int res = 0;
		for (int i = left; i <= right; i++) {
			arr[i] = arr[i] - 1;
		}
		for (int i = left; i <= right; i++) {
			if (arr[i] == 0) {
				res += process(arr, pre, i - 1);
				pre = i + 1;
			}
		}
		res += process(arr, pre, right) + 1;
		return res;
	}

	//100 99  1 3 100 2 3 4
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int cnt = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[i - 1] > arr[i]) {
				cnt += arr[i - 1] - arr[i];
			}
		}
		System.out.println(cnt + arr[n - 1]);
	}
}
