import java.util.HashMap;
import java.util.Map;

/**
 * 原理：计算以数组中每一个位置结尾为给定值的最长子数组， 从前找当前位置的累加和-k数值第一次出现的位置， 当前位置-找见这个位置即为最长
 *
 * @description: { 未排序数组中累加和为给定值的最长子数组}
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/07/01 09:30
 * @lastModified
 * @history
 */
public class LengthSumSubArrayLength {
  private static int max_length(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    // 记录最早出现累加和出现的位置
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    int len = 0;
    // 初始化，填入累加和为0的位置为-1
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      //
      sum += nums[i];
      if (map.containsKey(sum - k)) {
        len = Math.max(len, i - map.get(sum - k));
      }
      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }
    return len;
  }
}
