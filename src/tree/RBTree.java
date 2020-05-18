package tree;

/**
 *
 * @author liudongyang
 * 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private RBNode root;

	public static class RBNode<K extends Comparable<K>, V> {
		private RBNode parent;
		private RBNode left;
		private RBNode right;
		private K k;
		private V v;
		private boolean color;

		public RBNode() {
		}

		public RBNode(RBNode parent, RBNode left, RBNode right, K k, V v, boolean color) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.k = k;
			this.v = v;
			this.color = color;
		}

		public RBNode getParent() {
			return parent;
		}

		public void setParent(RBNode parent) {
			this.parent = parent;
		}

		public RBNode getLeft() {
			return left;
		}

		public void setLeft(RBNode left) {
			this.left = left;
		}

		public RBNode getRight() {
			return right;
		}

		public void setRight(RBNode right) {
			this.right = right;
		}

		public K getK() {
			return k;
		}

		public void setK(K k) {
			this.k = k;
		}

		public V getV() {
			return v;
		}

		public void setV(V v) {
			this.v = v;
		}

		public boolean isColor() {
			return color;
		}

		public void setColor(boolean color) {
			this.color = color;
		}
	}


	public void inOrderPrint() {
		inOrderPrint(this.root);
	}

	public void inOrderPrint(RBNode node) {
		if (node == null) return;
		inOrderPrint(node.left);
		System.out.println("key:" + node.k + " " + "value:" + node.v);
		inOrderPrint(node.right);
	}


	/**
	 *      P                P
	 *      |				 |
	 *      x				 y
	 *     | \     ---->    | \
	 *   lx   y 		   x  ry
	 *       | \		  | \
	 *		ly ry		 lx ly
	 *
	 *	具体过程
	 *	1.先解决y的左子树
	 *  将y的左子树的节点的父节点指向x，并将x的右子树指向y的左子树（ly）。
	 *  2.把y提升上去
	 *  如果x节点的父节点不为空，把y的父节点指向x的父节点（P），并把P节点指向y(需要知道x是P的左节点还是右节点)
	 *  3.维护x、y的父子关系
	 *  把x的父节点设置为y，把y的左子树设置为x
	 */
	public void leftRotate(RBNode x) {
		//RBNode left = node.left;
		RBNode y = x.right;
		// 第一步
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}

		// 第二步
		if (x.parent != null) {
			y.parent = x.parent;
			if (x.parent.left == x) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		} else {
			// 更新根节点
			this.root = y;
		}
		// 第三步
		x.parent = x.right;
		y.left = x;
	}
}
