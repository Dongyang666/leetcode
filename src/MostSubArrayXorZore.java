import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: { 最多可以把一个数组划分为多少个异或值为0的子数组 }
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/7/9 20:23
 * @lastModified
 * @history 一个小时0。34万 7万
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
