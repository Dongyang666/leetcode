package tree;

import java.util.Stack;

/**
 * @author liudongyang
 * 单调栈
 */
public class MonotonicStack {


	public static void mostNearLarge(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			while (!stack.isEmpty() && stack.peek() < num) {
				int cur = stack.pop();
				int left = stack.isEmpty() ? Integer.MIN_VALUE : stack.peek();
				int right = num;
				System.out.println(String.format("%s-%s-%s", cur, left, right));
			}
			stack.push(num);
		}
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			int left = stack.isEmpty() ? Integer.MIN_VALUE : stack.peek();
			System.out.println(String.format("%s-%s-%s", cur, left, Integer.MIN_VALUE));
		}
	}

	public static void mostNearSmall(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			while (!stack.isEmpty() && stack.peek() > num) {
				int curPrint = stack.pop();
				int left = stack.isEmpty() ? Integer.MIN_VALUE : stack.peek();
				int right = num;
				System.out.println(String.format("%s-%s-%s", curPrint, left, right));
			}
			stack.push(num);
		}
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			int left = stack.isEmpty() ? Integer.MIN_VALUE : stack.peek();
			System.out.println(String.format("%s-%s-%s", cur, left, Integer.MIN_VALUE));
		}

	}

	public static void main(String[] args) {
		mostNearSmall(new int[]{1, 3, 5, 9, 2, 4, 8});
	}
}
