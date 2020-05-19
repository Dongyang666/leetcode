package array;

/**
 * @description: { }
 * @author: liudongyang
 * @date: 2019/12/3 14:49
 */
public class RevolveBinarySearch {
    public static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        boolean flag = true;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] < arr[left]) {
                right = mid - 1;
            }
            if (arr[mid] > arr[left]) {
                left = mid + 1;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}, 0));
    }
}
