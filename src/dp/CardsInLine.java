package dp;

/**
 * @author liudongyang
 * <p>
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿 走每张纸牌
 * ，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最 右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 * <p>
 * for example: arr=[1,2,100,4]。 开始时玩家A只能拿走1或4。如果玩家A拿走1，则排列变为[2,100,4]，接下来玩 家B可以拿走2或4，
 * 然后继续轮到玩家A。如果开始时玩家A拿走4，则排列变为 [1,2,100]，接下来玩家B可以拿走1或100，然后继续轮到玩家A。
 * 玩家A作为绝顶 聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，让排 列变为[2,100,4]，
 * 接下来玩家B不管怎么选，100都会被玩家A拿走。玩家A会获 胜，分数为101。所以返回101。
 */
public class CardsInLine {
	//暴力递归
	public static int way1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
	}

	private static int first(int[] arr, int L, int R) {
		if (L == R) {
			return 1;
		}
		return Math.max(arr[L] + second(arr, L + 1, R), arr[R] + second(arr, L, R - 1));
	}

	private static int second(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		return Math.min(0 + first(arr, L + 1, R), 0 + first(arr, L, R - 1));
	}

	public static void main(String[] args) {
		System.out.println(way1(new int[]{1, 100, 2}));
		System.out.println(way2(new int[]{1, 100, 2}));
	}

	/**
	 * 动态规划解决方法
	 * 生成两个dp数组互相推最终填满数组
	 *
	 * @param arr
	 * @return
	 */
	public static int way2(int[] arr) {
		int[][] firstDp = new int[arr.length][arr.length];
		int[][] secondDp = new int[arr.length][arr.length];
		for (int i = 0; i < firstDp.length; i++) {
			firstDp[i][i] = 1;
		}
		for (int j = 1; j < arr.length; j++) {
			int tmp = j;
			for (int i = 0; i < arr.length - j; i++) {
				firstDp[i][tmp] = Math.max(arr[tmp] + secondDp[i][tmp - 1], arr[i] + secondDp[i + 1][tmp]);
				secondDp[i][tmp] = Math.min(firstDp[i][tmp - 1], firstDp[i + 1][tmp]);
				tmp++;
			}
		}
		return Math.max(firstDp[0][arr.length - 1], secondDp[0][arr.length - 1]);
	}
}
