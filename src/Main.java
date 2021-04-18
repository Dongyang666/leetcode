import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int R = sc.nextInt();
        int[] A = new int[m];
        int[] B = new int[n];
        for (int i = 0; i < m; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            B[i] = sc.nextInt();
        }

        int startB = 0;
        for (int i = 0; i < A.length && startB < B.length; i++) {
            int cur = A[i];
            int upperIndex = upper(B, startB, cur);
            if (upperIndex >= startB && B[upperIndex] - cur <= R && B[upperIndex] - cur >= 0) {
                System.out.println(cur + " " + B[upperIndex]);
            }
            startB = upperIndex + 1;
        }
    }

    public static int upper(int[] arr, int left, int target) {
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}