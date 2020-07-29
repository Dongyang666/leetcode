package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liudongyang
 */
public class QuickSort {
    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = partion(nums, left, right);
            quickSort(nums, left, mid - 1);
            quickSort(nums, mid + 1, right);
        }
    }

    private static int partion1(int[] nums, int left, int right) {
        dealPivot(nums, left, right);
        //标记值
        int pivot = left;
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[i] <= nums[pivot]) {
                i++;
            }
            while (i < j && nums[j] >= nums[pivot]) {
                j--;
            }
            //如果
            if (i < j) {
                swap(nums, i++, j--);
            }
        }
        if (nums[i] < nums[pivot]) {
            swap(nums, i, pivot);
        } else {
            swap(nums, i - 1, pivot);
            return i - 1;
        }
        return i;
    }

    /**
     * 双指针
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int partion2(int[] arr, int left, int right) {
        dealPivot(arr, left, right);
        //标记值
        int pivot = arr[left];
        int gt = right;
        int lt = left + 1;
        while (true) {
            while (lt <= right && arr[lt] < pivot) lt++;
            while (gt > left && arr[gt] > pivot) gt--;
            if (lt >= gt) break;
            swap(arr, lt++, gt--);
        }
        swap(arr, left, gt);
        return gt;
    }

    /**
     * 单指针
     *
     * @param left
     * @param right
     * @return
     */
    private static int partion(int[] arr, int left, int right) {
        dealPivot(arr, left, right);
        //标记值
        int pivot = arr[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                ++j;
                swap(arr, i, j);
            }
        }
        swap(arr, left, j);
        return j;
    }

    private static void dealPivot(int[] nums, int left, int right) {
        int mid = (right - left) / 2 + left;
        if (nums[mid] < nums[left]) {
            swap(nums, left, mid);
        }
        if (nums[left] > nums[right]) {
            swap(nums, left, right);
        }
        if (nums[mid] > nums[right]) {
            swap(nums, right, mid);
        }
        swap(nums, left, mid);
    }

    public static void main(String[] args) {
        int count = 0;
        while (true) {
            int[] res = getRandomRrray();
            bubleSort(res);
            int[] res2 = Arrays.copyOf(res, res.length);
            quickSort(res2, 0, res.length - 1);
            if (!Arrays.equals(res, res2)) {
                System.out.println(Arrays.toString(res));
                System.out.println(Arrays.toString(res2));
            }
            System.out.println(count++);
        }
    }

    public static void bubleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    flag = false;
                    swap(nums, j, j + 1);
                }
            }
            if (flag) {
                break;
            }
        }

    }

    public static int[] getRandomRrray() {
        Random random = new Random();
        int[] ints = new int[random.nextInt(10)];
        for (int i = 0; i < ints.length; i++) {
            int res = random.nextInt(1000);
            int x = res % 2;
            ints[i] = x == 0 ? res : res * -1;
        }
        return ints;

    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

}
