package sort;

import java.util.Arrays;

/**
 * @description: { }
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/06/05 10:22
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 5, 4, 3, 2, 0, 9, 5, 6, 8, 9, 3, 4};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] nums) {
        int len = nums.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapIfyMin(nums, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapIfyMin(nums, 0, i);
        }
    }

    /**
     * @description: { 大根堆}
     * @author: dyliu7@iflytek.com
     * @param: [nums, i, len]
     * @return: void
     * @date: 2019/6/5 10:24
     */
    private static void heapIfyMax(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < heapSize) {
            if (nums[left] > nums[i]) {
                largest = left;
            }
            if (right < heapSize && nums[right] > nums[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(nums, i, largest);
            } else {
                break;
            }
            left = largest * 2 + 1;
            right = largest * 2 + 2;
            i = largest;
        }
    }

    private static void heapIfyMin(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int smallest = i;
        while (left < heapSize) {
            if (nums[left] < nums[smallest]) {
                smallest = left;
            }
            if (right < heapSize && nums[right] < nums[smallest]) {
                smallest = right;
            }
            if (smallest != i) {
                swap(nums, i, smallest);
            } else {
                break;
            }
            left = smallest * 2 + 1;
            right = smallest * 2 + 2;
            i = smallest;

        }

    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
