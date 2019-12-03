/**
 * test class
 */
class Solution {
	public static int test() {
		int a = 10;
		int b = 20;
		int c = 2;
		int d = 3;
		return (a + b) * 10;
	}

	public static void main(String[] args) {
		test();
	}

/*	public static void main(String[] args) {
		int[][] ints = {{1, 4}, {4, 5}};
		ArrayList<int[]> list = new ArrayList<>();
		Arrays.sort(ints, (o1, o2) -> o1[0] > o2[0] ? 1 : o1[0] == o2[0] ? o1[1] > o2[1] ? 1 : -1 : -1);
		int curMaxIndex = Integer.MAX_VALUE;
		int pre = 0;
		for (int i = 0; i < ints.length; i++) {
			int[] res = ints[i];
			if (res[0] > curMaxIndex) {
				int[] m = new int[2];
				m[0] = pre;
				m[1] = curMaxIndex;
				pre = res[0];
				list.add(m);
			}
			curMaxIndex = res[1];
		}
		if (pre == ints[ints.length - 1][0]) {
			list.add(new int[]{pre, curMaxIndex});
		}
		int[][] ints1 = list.toArray(new int[0][]);
		for (int i = 0; i < ints1.length; i++) {
			System.out.println(Arrays.toString(ints1[i]));
		}
	}*/
}
