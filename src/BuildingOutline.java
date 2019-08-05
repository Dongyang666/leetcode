import java.util.*;

/**
 * 大楼轮廓：水平面上有 N 座大楼，每座大楼都是矩阵的形状，可以用三个数字表示 (start, end, height) ，分别代表其在 x
 * 轴上的起点，终点和高度。大楼之间从远处看可能会重叠，求出 N 座大楼的外轮廓线。
 * 外轮廓线的表示方法为若干三元组，每个三元组包含三个数字 (start, end, height)
 * ，代表这段轮廓的起始位置，终止位置和高度。
 */

/**
 * 解题思路：由于求外轮廓只需要知道在每一个与x轴有交点的楼的边界，是否有高度变化。
 * 所以问题转换为按照x轴的大小顺序对一次出现的楼的边界进行排序
 * ，首先按照x从左到右排序（从小到大），如果位置相同则向上的边界优先拍到前面。
 */
public class BuildingOutline {

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            // 构造节点类，
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        Arrays.sort(nodes, new NodeComparator());
        TreeMap<Integer, Integer> hmMap = new TreeMap<>();
        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            //
            if (nodes[i].isUp) {
                if (!htMap.containsKey(nodes[i].height)) {
                    htMap.put(nodes[i].height, 1);
                } else {
                    htMap.put(nodes[i].height, htMap.get(nodes[i].height) + 1);
                }
            } else {
                if (htMap.get(nodes[i].height) == 1) {
                    htMap.remove(nodes[i].height);
                } else {
                    htMap.put(nodes[i].height, htMap.get(nodes[i].height) - 1);
                }
            }
            if (htMap.isEmpty()) {
                hmMap.put(nodes[i].posi, 0);
            } else {
                hmMap.put(nodes[i].posi, htMap.lastKey());
            }
        }
        Integer h = 0;
        int start = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hmMap.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeigith = entry.getValue();
            if (h != curMaxHeigith) {
                // h==0没有老轮廓的结算 只有新轮廓的产生
                if (h != 0) {
                    List<Integer> record = new ArrayList<>();
                    record.add(start);
                    record.add(curPosition);
                    record.add(h);
                    res.add(record);
                }
                h = curMaxHeigith;
                start = curPosition;
            }
        }
        return res;
    }

    public class Node {
        public boolean isUp;
        public int posi;
        public int height;

        Node(boolean b, int p, int h) {
            isUp = b;
            posi = p;
            height = h;
        }
    }

    /** 比较器：如果两个节点位置不同则按照位置从小到大排序，如果位置相同则上升的排序靠前 */
    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) {
                return o1.posi - o2.posi;
            }
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }
}
