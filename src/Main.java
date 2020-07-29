import java.util.*;

class Main {


    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length <= k ? 1 : nums.length - k + 1;
        int left = 0;
        int cur = 0;
        int[] res = new int[len];
        int index = 0;
        int right = Math.min(k - 1, nums.length - 1);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        while (right < nums.length && left <= right) {
            while (cur <= right) {
                if (priorityQueue.peek() < nums[cur])
                    priorityQueue.offer(nums[cur]);
                cur++;
            }
            res[index++] = priorityQueue.peek();
            priorityQueue.remove(nums[left++]);
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        try {
            System.out.println(calculation("true or false".split(" ")));
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    public static boolean calculation(String[] strs) throws Exception {
        if ((strs.length & 1) == 0) throw new Exception("error");

        boolean ans = false;
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i].equals("and")) {
                String pre = tmp.remove(tmp.size() - 1);
                String last = strs[i + 1];
                if ("or".equals(pre) || "and".equals(pre) || "and".equals(last) || "or".equals(last))
                    throw new Exception("error");
                i += 1;
                tmp.add(String.valueOf(Boolean.parseBoolean(pre) & Boolean.parseBoolean(last)));
                continue;
            }
            tmp.add(strs[i]);
        }
        if (tmp.size() == 1 && (tmp.get(0).equals("true") || tmp.get(0).equals("false")))
            return Boolean.parseBoolean(tmp.get(0));
        if ((tmp.size() & 1) == 0) throw new Exception("e");
        for (int i = 0; i < tmp.size(); ++i) {
            if (tmp.get(i).equals("or")) {
                String pre = tmp.get(i - 1);
                String last = tmp.get(i + 1);
                if ("or".equals(pre) || "and".equals(pre) || "and".equals(last) || "or".equals(last))
                    throw new Exception("error");
                ans |= Boolean.parseBoolean(pre) | Boolean.parseBoolean(last);
                i += 1;
            }
        }
        return ans;

    }


    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        boolean k = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        int result = 0;
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        while (dividend <= divisor) {
            int temp = divisor;
            int c = 1;
            while (dividend - temp <= temp) {
                temp = temp << 1;
                c = c << 1;
            }
            dividend -= temp;
            result += c;
        }
        return k ? result : -result;
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int minCount = check(matrix, 14, n);
            if (minCount > k) {
                right = mid - 1;
            } else if (minCount < k) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int check(int[][] matrix, int mid, int n) {
        int i = n - 1;
        int j = 0;
        int count = 0;
        while (i != -1) {
            while (j < n && matrix[i][j] <= mid) {
                j++;
            }
            --i;
            count += j;
        }
        return count;
    }

    public static int minSumOfLengths(int[] arr, int target) {
        // 累加数组：[1, 3, 4, 7, 8, 10, 11, 3]
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, -1);
        int notFound = Integer.MAX_VALUE;

        //min是答案
        int min = notFound;

        //比如输入样例为arr = [1, 2, 1, 3, 1, 2, 1], target = 3时
        //对应的数组record是[0, 0, 2, 2, 1, 1, 1, 1]
        // 可以取前i个数字时组成target的1个子数组的最短长度
        int[] record = new int[arr.length + 1];
        Arrays.fill(record, notFound);
        //累加器
        int sum = 0;
        //比如输入样例为arr = [1, 2, 1, 3, 1, 2, 1], target = 3时
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //前面是否有正好小3的sum，即两者之间的数组，累加之后正好是一个target
            if (sums.get(sum - target) != null) {
                //产生了一个target
                int leftIdx = sums.get(sum - target);

                int lastRecord = record[leftIdx + 1];

                int nowRecord = i - leftIdx;
                record[i + 1] = nowRecord;
                if (lastRecord != notFound) {
                    min = Math.min(min, lastRecord + nowRecord);
                }
            }
            //比较后记录
            record[i + 1] = Math.min(record[i], record[i + 1]);
            sums.put(sum, i);
        }
        return min == notFound ? -1 : min;
    }

    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }
        // dp[i][j]
        // 以i结尾  支架两边相差为j的，可以构成的最高的广告牌
        int[][] dp = new int[rods.length + 1][sum + 1];
        for (int i = 1; i <= rods.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // dp[3][10] < 10
                if (dp[i - 1][j] < j) continue;
                // 不选当前的广告牌
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                // 选择当前的广告牌
                // 支架两边相差为j的时候可以选择两种情况来添加当前的广告牌。假设3 6 中间差值为3(j)
                // 可以把当前支架放到6上面 这样两边的差值就变成6了 也就是 j + rods[i - 1]
                int k = j + rods[i - 1];
                dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + rods[i - 1]);
                // 选择当前广告牌放到低的那边也就是3的那边这样差值就变成了0了 也就是 |j - rods[i - 1]|
                k = Math.abs(j - rods[i - 1]);
                dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + rods[i - 1]);
            }
        }
        return dp[rods.length][0] / 2;
    }

    public static int translateNum(int num) {
        char[] chs = String.valueOf(num).toCharArray();
        int f = 1;
        int s = 1;
        for (int i = 2; i <= chs.length; i++) {
            char ch = chs[i - 1];
            int curCount = f;
            int tmp = (chs[i - 1 - 1] - '0') * 10 + ch - '0';
            if (tmp > 9 && tmp < 26) {
                curCount += s;
            }
            s = f;
            f = curCount;

        }
        return f;

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


    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 11
     * 1
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int max = Math.max(a.length(), b.length());
        for (int i = 0; i < max; ++i) {
            carry += i < a.length() ? a.charAt(a.length() - i - 1) - '0' : 0;
            carry += i < b.length() ? b.charAt(b.length() - i - 1) - '0' : 0;
            sb.insert(0, (char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }


    // N : 位置为 1 ~ N，固定参数
    // cur : 当前在 cur 位置，可变参数
    // rest : 还剩 res 步没有走，可变参数
    // P : 最终目标位置是 P，固定参数
    // 只能在 1~N 这些位置上移动，当前在 cur 位置，走完 rest 步之后，停在 P 位置的方法数作为返回值返回
    public int walk(int N, int cur, int rest, int P) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }

        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }

        if (cur == N) {
            return walk(N, N - 1, rest - 1, P);
        }

        return walk(N, cur - 1, rest - 1, P) + walk(N, cur + 1, rest - 1, P);

    }

    /**
     * @param N 1-N N个位置
     * @param M 开始位置
     * @param K 必须走K步
     * @param P 最终到达P
     * @return
     */
    public int walkDp(int N, int M, int K, int P) {
        // 解空间
        /**
         * 要走i步走到j最终方案数是多少
         */
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) {
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }


    public int removeBoxes(int[] boxes) {
        if (boxes.length == 0) return 0;
        // 选中其中一段连续的
        int ans = 0;
        for (int i = 0; i < boxes.length; ++i) {
            int pivot = boxes[i];
            int last = i + 1;
            while (last < boxes.length && boxes[last] == pivot) {
                last++;
            }
            int[] tmp = new int[i + (boxes.length - last)];
            System.arraycopy(boxes, 0, tmp, 0, i);
            System.arraycopy(boxes, last, tmp, i + 1, boxes.length - last);
            ans = Math.max((last - i) * (last - i) + removeBoxes(tmp), ans);
        }
        return ans;
    }


}