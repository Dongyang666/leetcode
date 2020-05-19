package mcase;

import java.util.Scanner;

/**
 * @author liudongyang
 * 差分数组
 * 一条直线上等距离放置了n台路由器。路由器自左向右从1到n编号。第i台路由器到第j台路由器的距离为| i-j |。  每台路由器都有自己的信号强度，第i台路由器的信号强度为ai。所有与第i台路由器距离不超过ai的路由器可以收到第i台路由器的信号（注意，每台路由器都能收到自己的信号）。问一共有多少台路由器可以收到至少k台不同路由器的信号。
 * <p>
 * 输入描述:
 * 输入第一行两个数n , k（1≤n , k≤10^5）
 * <p>
 * 第二行n个数, a1 , a2 , a3……… , an（0≤ai≤10^9）
 * <p>
 * 输出描述:
 * 输出一个数，一共有多少台路由器可以收到至少k台不同路由器的信号。
 * <p>
 * 输入例子1:
 * 4 4
 * 3 3 3 3
 * <p>
 * 输出例子1:
 * 4
 */
public class MeiTuan5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] arr = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int in = sc.nextInt();
			arr[Math.max(0, i - in)]++;
			arr[Math.min(n, i + in + 1)]--;
		}
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sum >= k) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
