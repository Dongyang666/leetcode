package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p/>
 * Description:
 *
 * @author liudongyang
 * @date 2020/04/24
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (arr.length == 0) return;
        if (left == right) return;
        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int temIndex = 0;
        int index1 = left;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= right) {
            temp[temIndex++] = arr[index1] > arr[index2] ? arr[index2++] : arr[index1++];
        }
        while (index1 <= mid) {
            temp[temIndex++] = arr[index1++];
        }
        while (index2 <= right) {
            temp[temIndex++] = arr[index2++];
        }
        for (int value : temp) {
            arr[left++] = value;
        }
    }


    /*public static void main(String[] args) {
        int count = 0;
        while (true) {
            int[] res = getRandomRrray();
            bubleSort(res);
            int[] res2 = Arrays.copyOf(res, res.length);
            mergeSort(res2, 0, res.length - 1);
            if (!Arrays.equals(res, res2)) {
                System.out.println(Arrays.toString(res));
                System.out.println(Arrays.toString(res2));
            }
            System.out.println(count++);
        }
    }*/
    public static void main(String[] args) {
        int[] arr = new int[]{9, -5, -1, 8, 6, 2, 1, 4, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
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
        int[] ints = new int[random.nextInt(20)];
        for (int i = 0; i < ints.length; i++) {
            int res = random.nextInt(1000);
            ints[i] = (res & 1) == 1 ? res : res * -1;
        }
        return ints;

    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
