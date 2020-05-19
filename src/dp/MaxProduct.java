package dp;

import java.text.ParseException;

/**
 * @description: { 乘积最大子序列}
 * @author: liudongyang
 * @date: 2019/12/16 15:44
 */
public class MaxProduct {
    public static int maxProduct(int[] nums) {
        int max = nums[0], preMax = nums[0], preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curNum = nums[i];
            int tmpMax = preMax;
            //记录每一个结尾位置成绩的最大值和最小值----3中求最大值

            preMax = Math.max(preMin * curNum, Math.max(preMax * curNum, nums[i]));
            preMin = Math.min(preMin * curNum, Math.min(tmpMax * curNum, nums[i]));
            max = Math.max(max, preMax);
        }
        return max;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(maxProduct(new int[]{-1, -2, -9, -6}));
    }
}
