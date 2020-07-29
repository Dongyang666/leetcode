import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liudongyang
 * @date 2020/06/11
 */
class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public double minimalExecTime(TreeNode root) {
        double[] res = execTime(root, 2);
        return res[0];
    }


    public double[] execTime(TreeNode node, int n) {
        if (node == null) {
            // [0]执行完当前节点最小耗时，[1]当前node为根的时间串行之和
            return new double[]{0.0D, 0.0D};
        }
        // 获取左右子树的值
        double[] leftTime = execTime(node.left, n);
        double[] rightTime = execTime(node.right, n);
        // 左右子树节点之和
        double sum = leftTime[1] + rightTime[1];
        // 当前节点执行完的最小消耗时间
        double minTime = Math.max(Math.max(leftTime[0], rightTime[0]), sum / n) + node.val;
        return new double[]{minTime, sum + node.val};

    }


    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String doubleToString(String input) {
        return new DecimalFormat("0.00000").format(input);
    }


    public static int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; ++i) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int pop = stack.pop();
                if (stack.isEmpty()) break;
                int cur = height[pop];
                int leftH = height[stack.peek()];
                int rightH = height[i];
                ans += (Math.min(leftH, rightH) - cur) * (i - stack.peek() - 1);

            }
            stack.push(i);
        }
        System.out.println(stack);
        return ans;
    }


    /**
     * 思路：选择一个i 两个指针一个p指向i+1 一个q指向数组最后一个数，如果i、p、q的和大于零则左移q，如果小于零则右移p。
     **/
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        if (len < 3) return ans;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int val = nums[i];
            twoSum(nums, i + 1, len - 1, -val, val);
        }
        return ans;

    }

    public void twoSum(int[] nums, int left, int right, int target, int val) {
        System.out.println(Arrays.toString(nums));
        while (left < right) {
            System.out.println(left + "::" + right);
            int targetSum = nums[left] + nums[right];
            if (targetSum == target) {
                System.out.println(targetSum + "--" + target);
                List<Integer> tmp = new ArrayList<>();
                tmp.add(val);
                tmp.add(nums[left]);
                tmp.add(nums[right]);
                ans.add(tmp);
                right--;
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
                left++;
                while (left < right && nums[left - 1] == nums[left]) {
                    left++;
                }
            } else if (targetSum > target) {
                right--;
            } else {
                left++;
            }
        }
    }


    static List<List<String>> res = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {
        if (n < 1) return res;
        int[] line = new int[n];
        process(0, line, n, new LinkedList<>());
        return res;
    }

    public static void process(int i, int[] line, int n, LinkedList<String> list) {
        if (i == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 遍历每一个位置看看能不能放
        for (int k = 0; k < n; ++k) {
            if (isV(line, i, k)) {
                line[i] = k;
                char[] tmp = new char[n];
                Arrays.fill(tmp, '.');
                tmp[k] = 'Q';
                list.add(new String(tmp));

                process(i + 1, line, n, list);
                line[i] = 0;
                list.removeLast();
            }
        }
    }

    public static boolean isV(int[] line, int i, int k) {
        for (int p = 0; p < i; ++p) {
            System.out.println(line[p] - k);
            System.out.println(i - p);
            if (k == line[p] || Math.abs(line[p] - k) == Math.abs(i - p)) {
                return false;
            }
        }
        return true;
    }


    public static int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // 当前位置向下看
        int[][] down = new int[n][m];
        // 当前位置向上看
        int[][] up = new int[n][m];
        // 当前位置向左看
        int[][] left = new int[n][m];
        // 当前位置向右看
        int[][] right = new int[n][m];
        int cnt = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) cnt++;
                left[i][j] = grid[i][j] == 1 ? j == 0 ? 1 : left[i][j - 1] + 1 : 0;
                up[i][j] = grid[i][j] == 0 ? 0 : i == 0 ? 1 : up[i - 1][j] + 1;
            }
        }
        if (cnt == 0) return 0;
        for (int i = n - 1; i > -1; --i) {
            for (int j = m - 1; j > -1; --j) {
                right[i][j] = grid[i][j] == 1 ? j == m - 1 ? 1 : right[i][j + 1] + 1 : 0;
                down[i][j] = grid[i][j] == 1 ? i == n - 1 ? 1 : down[i + 1][j] + 1 : 0;
            }
        }
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 0) continue;

                for (int k = 1; i + k < n && j + k < m; ++k) {
                    if (right[i][j] >= k + 1 &&
                            down[i][j] >= k + 1 &&
                            up[i + k][j + k] >= k + 1 &&
                            left[i + k][j + k] >= k + 1
                    ) {
                        ans = Math.max(ans, (k + 1) * (k + 1));
                    } else break;
                }
            }
        }
        return ans;

    }


    public int rob(TreeNode root) {
        int[] res = select(root);
        // 1 偷 0 不偷
        return Math.max(res[0], res[1]);
    }

    public int[] select(TreeNode root) {
        if (root == null) return new int[2];
        int[] res = new int[2];

        int[] leftRes = select(root.left);
        int[] rightRes = select(root.right);

        // 不偷
        res[0] = Math.max(leftRes[0], leftRes[1]) + Math.max(rightRes[0], rightRes[1]);
        res[1] = leftRes[0] + rightRes[0] + root.val;
        return res;
    }

    public static int calculcation(String str) throws Exception {
        if (str.length() == 0) return 0;

        if (str.charAt(0) != 'A' && str.charAt(0) != 'S') {
            return Integer.parseInt(str);
        }

        int count = 0;
        // 获取分隔符
        int splitIndex = 4;
        for (; splitIndex < str.length(); ++splitIndex) {
            if (str.charAt(splitIndex) == '(') count++;
            if (str.charAt(splitIndex) == ')') count--;
            if (count < 0) throw new Exception("error");
            if (count == 0 && str.charAt(splitIndex) == ',') break;
        }

        String p1 = str.substring(4, splitIndex);
        String p2 = str.substring(splitIndex + 1, str.length() - 1);

        if (str.startsWith("ADD")) {
            return calculcation(p1) + calculcation(p2);
        } else {
            return calculcation(p1) - calculcation(p2);
        }
    }


    public static void main(String[] args) throws Exception {

        List<String> ans = new ArrayList<>();
        List<String> collect = ans.stream().filter(a -> a.length() == 0).collect(Collectors.toList());
    }


    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        List<List<Integer>> graph = new ArrayList<>();


        Set<Integer> ends = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < routes.length; ++i) {
            Arrays.sort(routes[i]);
            if (Arrays.binarySearch(routes[i], S) != -1) {
                //starts.add(i);
                queue.offer(i);
            }
            if (Arrays.binarySearch(routes[i], T) != -1) ends.add(i);
            graph.add(new ArrayList<>());
        }
        // Build the graph.  Two buses are connected if
        // they share at least one bus stop.
        for (int i = 0; i < routes.length; ++i) {
            for (int j = i + 1; j < routes.length; ++j) {
                if (check(routes[i], routes[j])) {
                    // 邻接表存
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }


        Set<Integer> isV = new HashSet<>();
        int ans = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            // 遍历当前队列中的元素
            for (int i = 0; i < n; ++i) {
                int poll = queue.poll();
                isV.add(poll);
                if (ends.contains(poll)) return ans;
                List<Integer> lines = graph.get(poll);
                for (Integer line : lines) {
                    if (!isV.contains(line)) {
                        queue.offer(line);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public boolean check(int[] A, int[] B) {
        int indexA = 0;
        int indexB = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA] == B[indexB]) return true;
            if (A[indexA] > B[indexB]) indexB++;
            else indexA++;
        }
        return false;
    }

}
