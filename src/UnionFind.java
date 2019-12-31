/**
 * 并查集结构
 *
 * @author liudongyang
 */
public class UnionFind {
    /**
     * 联通分量
     */
    private int count;
    /**
     * 记录父节点
     * parent[x] = y,表示 x节点的父节点是y
     */
    private int[] parent;

    /**
     * 记录数的重量
     */
    private int[] size;

    /**
     * 构造方法
     */
    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        //初始化默认让父节点指向自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) {
            return;
        }
        //p数多于q树,小树接在大树下面
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        //parent[rootP] = rootQ;
        //联通分量减少
        count--;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public int findRoot(int x) {
        while (parent[x] != x) {
            //没到最上层
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }


}
