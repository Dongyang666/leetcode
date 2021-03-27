import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void main1(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n <= 1) return;
		Node root = new Node(sc.nextInt());
		for (int i = 0; i < n - 1; i++) {
			int in = sc.nextInt();
			Node cur = new Node(in);
			Node preRoot = root;
			while (true) {
				if (preRoot.val < in && preRoot.right != null) {
					preRoot = preRoot.right;
				} else if (preRoot.val > in && preRoot.left != null) {
					preRoot = preRoot.left;
				} else {
					if (in > preRoot.val) {
						preRoot.right = cur;
					} else {
						preRoot.left = cur;
					}
					System.out.print(preRoot.val + " ");
					break;
				}
			}
		}
	}

	public static class Node {
		public Node left;
		public Node right;
		public int val;

		public Node(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[]{9, 5, 5, 32, 6, 8, 3};
		Arrays.sort(arr, (p1, p2) -> {

			return p2 - p1;
		});
		System.out.println(Arrays.toString(arr));

	}

	public static int find(String target, String source) {
		int targetIndex = target.length() - 1;
		int sourceIndex = source.length() - 1;
		for (int i = sourceIndex; i > -1; i--) {
			if (source.charAt(i) == target.charAt(targetIndex)) {
				targetIndex--;
			}
			if (targetIndex == -1) return i;
		}
		return -1;
	}

	public static class PlayerNode {
		// 选手编号
		public int id;
		// 选手总得分
		public int score;
		// 选手得分分布图
		public int[] scoresCount = new int[11];
	}

	public static String find(int players, int comments, int[][] scores) {
		if (comments < 3) return "-1";
		// 选手实体数组，用作排序使用
		PlayerNode[] playerNodes = new PlayerNode[players];
		// init 选手实体数组
		for (int i = 0; i < scores.length; i++) {
			PlayerNode playerNode = new PlayerNode();
			playerNode.id = i;
			int sum = 0;
			for (int j = 0; j < scores[i].length; j++) {
				//得分超出10分直接归零
				scores[i][j] = scores[i][j] > 10 ? 0 : scores[i][j];
				sum += scores[i][j];
				playerNode.scoresCount[scores[i][j]]++;
			}
			playerNode.score = sum;
			playerNodes[i] = playerNode;
		}
		// 选手排序
		Arrays.sort(playerNodes, (p1, p2) -> {
			// 总得分相同，谁得分多谁就牛皮
			if (p1.score == p2.score) {
				for (int i = 10; i > 0; i--) {
					if (p1.scoresCount[i] > p2.scoresCount[i]) {
						return -1;
					} else if (p1.scoresCount[i] < p2.scoresCount[i]) {
						return 1;
					}
				}
			}
			// 直接返回得分排序
			return p2.score - p1.score;
		});
		return String.format("%s,%s,%s", playerNodes[0].id + 1,
				playerNodes[1].id + 1, playerNodes[2].id + 1);
	}

}