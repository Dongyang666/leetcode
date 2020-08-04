package graph;

import java.util.*;

/**
 * @author liudongyang
 * @date 2020/08/04
 * 图的拓扑排序
 *
 * 你这个学期必须选修 numCourse 门课程，记为0到numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 */
public class CanFinishClass {
    // 邻接表
    public List<List<Integer>> edges;
    public boolean flag = true;
    // 当前遍历到的状态
    // 0未遍历 1遍历中 2遍历完成
    public int[] visitedStatus;

    /**
     * 方法1 dfs
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 拓扑排序先构建边之间的关系
        //init edges
        for (int i = 0; i < numCourses; ++i) edges.add(new ArrayList<>());
        // 构建边关系
        for (int[] edge : prerequisites) {
            edges.get(edge[1]).add(edge[0]);
        }
        // dfs遍历 如果再dfs过程中遇到节点的状态是1 则证明有环，不能完成课程
        for (int i = 0; i < visitedStatus.length && flag; i++) {
            if (visitedStatus[i] == 0) {
                dfs(i);
            }
        }
        return flag;
    }

    private void dfs(int node) {
        // 首先把当前节点的状态更新为遍历中
        visitedStatus[node] = 1;
        //去遍历他的前置节点，在遍历过程中如果有节点处在遍历中直接返回
        for (int preNode : edges.get(node)) {
            if (visitedStatus[node] == 0) {
                dfs(preNode);
                if (!flag) return;
            } else if (visitedStatus[node] == 1) {
                flag = false;
                return;
            }
        }
        // 遍历完设置为遍历完成
        visitedStatus[node] = 2;
    }

    /**
     * 方法2 bfs
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hasVisited = new HashSet<>();
        List<List<Integer>> edges = new ArrayList<>();
        // init edges
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
            hasVisited.add(i);
        }
        // bfs构建边关系和dfs刚好相反。因为bfs为了找见当前节点的下面节点
        for (int[] edge : prerequisites) {
            edges.get(edge[0]).add(edge[1]);
            hasVisited.remove(edge[1]);
        }
        queue.addAll(hasVisited);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                int nextNode = queue.poll();
                if (hasVisited.contains(nextNode)) return false;
                queue.add(nextNode);
                hasVisited.add(nextNode);
            }
        }
        return false;
    }

}
