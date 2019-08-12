package array;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: { 最多可以把一个数组划分为多少个异或值为0的子数组 }<br>
 *     * 原理：动态规划，，以每一个位置结尾都有两种情况，第一种包含当前位置，第二种不包含当前位置<br>
 *     * 假设当前位置为 i；<br>
 *     * 第一种包含当前位置i，那从当前位置向前找到位置k，k到i的异或值为0 所以包含当前位置的最大子数组数量为 most[K]+1<br>
 *     * 怎么找k:假设0~i的异或值为eor，那么在0~i-1上的最后一次异或值等于eor的位置到i的位置的异或值肯定是0（异或性质）<br>
 *     * 使用map集合把0~i的每一位异或值存起来即可。 <br>
 *     * 第二种不包含当前位置i：那结果等于i-1的值 每一个位置决策一下即可
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/7/9 20:23
 */
public class MostSubArrayXorZore {

  public static void main(String[] args) {
    //

    long time = 1564225166655L;
    Date date = new Date(time);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdf.format(date);
    System.out.println(dateStr);
  }

  public int mostEor(int[] nums) {
    int ans = 0;
    int eor = 0;
    int[] most = new int[nums.length];
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      //
      eor ^= nums[i];
      if (map.containsKey(eor)) {
        int pre = map.get(eor);
        most[i] = pre == -1 ? 1 : (most[pre] + 1);
      }
      if (i > 0) {
        most[i] = Math.max(most[i], most[i - 1]);
      }
      map.put(eor, i);
      ans = Math.max(ans, most[i]);
    }
    return ans;
  }
}
