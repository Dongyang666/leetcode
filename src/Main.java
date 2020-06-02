import java.util.*;

class Main {
	public String q() {
		char c = 34;
		return s + c + s + c + ';' + '}';
	}

	static String s = "class Solution { public String q() { char c = 34; return s+c+s+c+';'+'}'; } static String s = ";

	public static void main(String[] args) {
		/*PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
		int[][] res = new int[1000][1000];
		long start = System.nanoTime();
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				int s = res[i][j];
			}
		}
		long start1 = System.nanoTime();
		//long start1 = System.nanoTime() - start;
		for (int i = 0; i < res[0].length; i++) {
			for (int j = 0; j < res.length; j++) {
				int s = res[j][i];
			}
		}

		long end = System.nanoTime();
		System.out.println(start1 - start);
		System.out.println(end - start1);*/
		//minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404});
		
		videoStitching(new int[][]{{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}}, 9);

	}

	public static int videoStitching(int[][] clips, int T) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int[] c : clips) {
			Integer orDefault = map.getOrDefault(c[0], 0);
			if (c[1] >= orDefault) {
				map.put(c[0], c[1]);
			}
		}
		System.out.println(map.toString());
		int ans = 0;
		int curIndex = 0;
		List<Integer> list = new ArrayList<>(map.keySet());
		for (int i = 0; i < list.size(); i++) {
			//我能选择的是key值小于当前位置的值中的value值最大者。
			int curI = i;
			int preIndex = curIndex;
			while (curI < list.size() && list.get(curI) <= preIndex) {
				if (map.get(list.get(curI)) > curIndex) {
					curIndex = map.get(list.get(curI));
					i = curI;
				}
				curI++;
			}
			ans++;
			if (curIndex >= T) return ans;
		}
		return -1;
	}


	public static int minJumps(int[] arr) {
		if (arr.length == 1)
			return 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.computeIfAbsent(arr[i], k -> new HashSet<>()).add(i);
		}
		int[] dp = new int[arr.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[arr.length - 1] = 0;
		for (int x : map.get(arr[arr.length - 1])) {
			if (x == arr.length - 1)
				continue;
			dp[x] = 1;
		}
		for (int i = arr.length - 2; i >= 0; i--) {
			int cur = arr[i];
			//可以跳到后面去
			dp[i] = Math.min(dp[i], dp[i + 1] + 1);
			Set<Integer> set = map.get(cur);
			dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
			for (int x : map.get(cur)) {
				dp[x] = Math.min(dp[x], dp[i] + 1);
			}
			System.out.println(Arrays.toString(dp));
		}

		return dp[0];
	}


	public static int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	public static void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = partion(nums, left, right);
			quickSort(nums, left, mid - 1);
			quickSort(nums, mid + 1, right);
		}

	}

	public static int partion(int[] nums, int left, int right) {
		int lt = left + 1;
		int gt = right;
		int pivot = nums[left];
		while (true) {
			while (lt <= gt && nums[lt] < pivot) {
				lt++;
			}
			while (lt <= gt && nums[gt] > pivot) {
				gt--;
			}
			if (lt > gt) {
				break;
			}
			swap(nums, lt++, gt--);
		}
		swap(nums, left, gt);
		return gt;
	}

	/*public void dealP(int[] nums,int left,int right){
		int midIndex = left;
		int mid = (right - left) >> 1 + left;
		if(nums[midIndex]>num)
	}*/
	public static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;

	}
}