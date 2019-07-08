/**
 * @description: { }
 * @author: dyliu7@iflytek.com
 * @created: Created in 2019/06/05 08:53
 * @lastModified
 * @history
 */
public class SortColor {
  public static void sort(int[] nums) {
    int p0 = 0;
    int p2 = nums.length - 1;
    int cur = 0;
    while (cur <= p2) {
      int num = nums[cur];
      if (num == 0) {
        swap(nums, cur++, p0++);
      } else if (num == 2) {
        swap(nums, cur, p2--);
      } else {
        cur++;
      }
    }
  }

  private static void swap(int[] nums, int left, int right) {
    int tmp = nums[left];
    nums[left] = nums[right];
    nums[right] = tmp;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {0, 0};
    sort(nums);
    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
  }
}
