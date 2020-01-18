import java.util.Arrays;

/**
 * @author liudongyang
 * 线段树
 * 参考：https://blog.csdn.net/qq_41479464/article/details/88586512
 */
public class SegmentTree {
	private int[] tree;
	/**
	 * 存储传入对象副本
	 */
	private int[] data;

	/**
	 * 构造函数
	 *
	 * @param arr
	 */
	public SegmentTree(int[] arr) {
		data = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			data[i] = arr[i];
		}
		//线段树初始化，最坏情况下大小为原数组长度的四倍
		tree = new int[4 * arr.length];
		buildSegmentTree(0, 0, data.length - 1);
	}

	/**
	 * 在treeIndex的位置创建表示区间[l,r]的线段树
	 *
	 * @param treeIndex
	 * @param l
	 * @param r
	 */
	private void buildSegmentTree(int treeIndex, int l, int r) {
		//递归结束的条件
		if (l == r) {
			tree[treeIndex] = data[l];
			return;
		}
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		//这样定义中间位置放置溢出
		int mid = l + (r - l) / 2;
		//创建左子树
		buildSegmentTree(leftTreeIndex, l, mid);
		//创建右子树
		buildSegmentTree(rightTreeIndex, mid + 1, r);
		//这个E的类型无法用求和
		tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
	}

	public int getSize() {
		return data.length;
	}

	public int get(int index) {
		return data[index];
	}

	private int leftChild(int index) {
		return 2 * index + 1;
	}

	private int rightChild(int index) {
		return 2 * index + 2;
	}

	/**
	 * 返回区间[queryL,queryR]的值
	 *
	 * @param queryL
	 * @param queryR
	 * @return
	 */
	public int query(int queryL, int queryR) {
		return query(0, 0, data.length - 1, queryL, queryR);
	}

	/**
	 * 在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryl,queryr]的值
	 *
	 * @param treeIndex
	 * @param l
	 * @param r
	 * @param queryL
	 * @param queryR
	 * @return
	 */
	private int query(int treeIndex, int l, int r, int queryL, int queryR) {
		if (l == queryL && r == queryR) {
			return tree[treeIndex];
		}
		int mid = l + (r - l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		//要查的节点的最左边的值大于中间值 则说明要查寻的区间在右子树中。
		if (queryL >= mid + 1) {
			return query(rightTreeIndex, mid + 1, r, queryL, queryR);
		} else if (queryR <= mid) {
			return query(leftTreeIndex, l, mid, queryL, queryR);
		}
		//分散在两个区间
		int leftResult = query(leftTreeIndex, l, mid, queryL, mid);
		int rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
		return leftResult + rightResult;
	}

	@Override
	public String toString() {
		return Arrays.toString(tree);
	}


	/**
	 * 在[insertL,insertR] 每个位置都加addNum的大小
	 *
	 * @param insertL
	 * @param insertR
	 * @param addNum
	 */
	private void add(int insertL, int insertR, int addNum) {
		add(0, 0, data.length - 1, insertL, insertR, addNum);
	}

	/**
	 * 在原数组中的insertIndex位置加上addNum的大小
	 *
	 * @param insertIndex
	 * @param addNum
	 */
	private void add(int insertIndex, int addNum) {
		add(0, 0, data.length - 1, insertIndex, insertIndex, addNum);
	}

	private void add(int index, int l, int r, int insertL, int insertR, int num) {
		if (l == r && insertL == l) {
			tree[index] += num;
			return;
		}
		tree[index] += (insertR - insertL + 1) * num;
		int mid = l + ((r - l) >> 1);
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		if (insertL >= mid + 1) {
			add(right, mid + 1, r, insertL, insertR, num);
		} else if (insertR <= mid) {
			add(left, l, mid, insertL, insertR, num);
		} else {
			add(left, l, mid, insertL, mid, num);
			add(right, mid + 1, r, mid + 1, insertR, num);
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, -5, -9, 10, 125};
		SegmentTree segMentTree = new SegmentTree(nums);
		System.out.println(segMentTree);
		System.out.println(segMentTree.query(0, 0));
		segMentTree.add(0, 0, 6);
		System.out.println(segMentTree);
		System.out.println(segMentTree.query(0, 0));

	}

}
