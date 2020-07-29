package array;

import java.util.Stack;

/**
 * @author liudongyang
 * 42.接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 */
public class Trap {
    public static int trap(int[] h) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < h.length; i++) {
            //可以开始结算了
            while (!stack.isEmpty() && h[i] > h[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int cur = h[top];
                int left = h[stack.peek()];
                int right = h[i];
                int distance = i - stack.peek() - 1;
                res += (Math.min(left, right) - cur) * distance;
            }
            stack.push(i);

        }
        return res;
    }

    public static int trap1(int[] arr) {
        int leftMax = 0, rightMax = 0, left = 0;
        int right = arr.length - 1;
        int res = 0;
        while (left <= right) {
            if (leftMax < rightMax) {
                res += Math.max(leftMax - arr[left], 0);
                leftMax = Math.max(leftMax, arr[left++]);
            } else {
                res += Math.max(rightMax - arr[right], 0);
                rightMax = Math.max(rightMax, arr[right--]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{3, 0, 2, 0, 1, 0, 3}));

    }
}
