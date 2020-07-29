package array;

import java.util.Random;

/**
 * @author liudongyang
 * @date 2020/05/19
 */
public class FindKthLargest {
    public static void main(String[] args) {
        int[] ints = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        QuickPickSolution.findKthLargest(ints, 4);
    }

    public static class QuickPickSolution {
        public static int findKthLargest(int[] arr, int k) {
            // 求第k大的值就是从后向前看第k个 也就是要求 arr[arr.length - k]
            return pick(arr, 0, arr.length - 1, arr.length - k);
        }

        public static int pick(int[] arr, int l, int r, int k) {
            int p = part(arr, l, r);
            if (p == k) {
                return arr[p];
            }
            return p > k ? pick(arr, l, p - 1, k) : pick(arr, p + 1, r, k);
        }

        private static int part(int[] arr, int l, int r) {
            int p = new Random().nextInt(r - l + 1) + l;
            swap(arr, p, l);
            int pivot = arr[l];
            int j = l;
            for (int i = l + 1; i <= r; i++) {
                if (arr[i] < pivot) {
                    j++;
                    swap(arr, j, i);
                }
            }
            swap(arr, l, j);
            return j;
        }


    }


    /**
     * 维护一个小根堆
     */
    public static class MinHeapSolution {

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


    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    
}
