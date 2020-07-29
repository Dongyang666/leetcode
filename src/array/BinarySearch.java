package array;

/**
 * @author liudongyang
 */
public class BinarySearch {


    /**
     * 基础二分查找
     *
     * @param nums   被查找数组
     * @param target 目标值
     * @return 目标值在数组中的位置，找不见则返回-1
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //为什么是<=不是<
        /**
         *查询的区间为闭区间[left,right]，查找到的条件是nums[mid] == target
         * 假如数组中不包含target
         * 	 1.如果while的条件是<= :则跳出循环的条件是left>right 也就是 left = right +1
         *   区间写法就是[right+1,right]，数字表示[3,2]这个时候会判断判断到2这个索引。
         *   2.如果while的条件是<   则跳出循环的条件是left = right
         *   区间写法就是[right,right] 数组表示就是[2,2]，这个时候不会判断2这个索引。跳出会少判断2这个索引的值是否等于目标
         *   数字，需要加返回条件进行规避,对left=right随便一个下标的值和目标值判断即可，nums[left] == target ? left : -1;
         */
        //while(left < right)
        while (left <= right) {
            int mid = (right - left) >>> 1 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        //return nums[left] == target ? left : -1;
        return -1;
    }

    public static int searchLeftBound(int[] nums, int target) {
        //为什么要right = nums.length;用左闭右开不用左闭右闭呢？
        //比如说[2,3,4,6] target==8;返回值为4 而不是-1，如果target=1则返回0,所以返回值的值域为[0,nums.length]
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                //左闭右开
                left = mid + 1;
            }
        }
		/*
		if(left == nums.length) return -1;
		return nums[left] == target ? left : -1;
		 */
        return left;
    }

    public static int searchRightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            //左指针一直逼近最右边的值
            if (nums[mid] == target) {
                //为什么是+1 不是mid 因为区间是左闭右开左边是闭区间所以要+1;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        /**
         * if(left == 0 ) return -1;
         * return nums[left -1] == target? left -1 : -1;
         */
        //为什么要-1 ，因为当mid的值和target相同时left = mid + 1; left - 1 可能是结果。
        return left - 1;
    }


    public static int lower(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int upper(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
    

    public static void main(String[] args) {
        int[][] arr = new int[][]{{2, 5, 8}, {4, 0, -1}};
        System.out.println(upper(new int[]{1, 2, 3, 3, 4, 5, 5, 5, 5, 7, 8, 8, 8, 8, 8}, 8));
    }
}
