package array;

/**
 * @author liudongyang
 * @date 2020/05/19
 */
public class FindKthLargest {

    public static int findKthLargest(int[] arr, int k) {
        int ans = arr[0];
        int len = arr.length;
        // 从第一个非叶子节点调整
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapIfy(arr, i, len);
        }

        for (int i = len - 1; i >= 0 && k > 0; k--, i--) {
            swap(arr, 0, i);
            ans = arr[i];
            heapIfy(arr, 0, i);
        }
        return ans;
    }

    public static void heapIfy(int[] arr, int index, int len) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < len) {
            if (arr[largest] < arr[left]) {
                largest = left;
            }
            if (right < len && arr[largest] < arr[right]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, index, largest);
            } else {
                break;
            }
            index = largest;
            left = largest * 2 + 1;
            right = largest * 2 + 2;
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
