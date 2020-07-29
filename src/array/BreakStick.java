package array;

import java.util.Stack;

/**
 * @author liudongyang
 * @date 2020/05/25
 * 头条笔试题 折木棍
 *
 * 在你的面前从左到右摆放着n根长短不一的木棍，你每次可以折断一根木棍，并将折断后得到的两根木棍一左一右放在原来的位置
 * （即若原木棍有左邻居，则两根新木棍必须放在左邻居的右边，若原木棍有右邻居，新木棍必须放在右邻居的左边，所有木棍保持左右排列）。
 * 折断后的两根木棍的长度必须为整数，且它们之和等于折断前的木棍长度。你希望最终从左到右的木棍长度单调不减，那么你需要折断多少次呢？
 *
 * 从后向前单调栈遍历
 * 遇到大于栈顶的说明需要折断了 但是怎么这折能保证折断的左边最小况且还能整体小于有边界呢？
 * 均分即可 例如 13 可以切两段 1-12 2-11 3-10 4-9 5-8 6-7 7-6后面不符合了 左边能切割的最大值就是均等分
 *
 * 1 2 3 4 5 5
 */
public class BreakStick {

    public static int breakStick(int[] arr) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            int curStick = arr[i];
            if (stack.isEmpty() || stack.peek() > curStick) {
                stack.push(curStick);
            } else {
                // 先看砍几刀
                int t = (arr[i] - 1) / stack.peek();
                ans += t;
                // 把木棍均分了
                stack.push(arr[i] / (t + 1));
            }

        }
        System.out.println(stack);
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(breakStick(new int[]{3, 12, 13, 9, 12}));
    }
}
