package array;


/**
 * <p/>
 * Description:
 *
 * @author liudongyang
 * @date 2020/04/27
 */
public class SearchSpinArrays {
    public int search(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        int rotateIndex = findRotateIndex(arr);
        int leftRangeFindResult = binarySearch(arr, 0, rotateIndex, target);
        return leftRangeFindResult != -1 ? leftRangeFindResult : binarySearch(arr, rotateIndex + 1, arr.length - 1, target);
    }

    public int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int findRotateIndex(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        if (arr[left] <= arr[right]) {
            return 0;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] < arr[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0;

    }


}
