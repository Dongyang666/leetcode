import java.util.Arrays;
import java.util.Scanner;

/**
 * test class
 */
class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n + 1];
		int max = 0;
		for (int i = 0; i < n - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[b] = arr[a] + 1;
			max = Math.max(max, arr[b]);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(max);
	}


}
