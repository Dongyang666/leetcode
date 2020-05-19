package tree;

/**
 *
 * @author liudongyang
 * 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private RBNode<K, V> root;

	public RBNode<K, V> getRoot() {
		return root;
	}

	public static class RBNode<K extends Comparable<K>, V> implements TreeOperation.PrintTreeNode {
		private RBNode<K, V> parent;
		private RBNode<K, V> left;
		private RBNode<K, V> right;
		private K k;
		private V v;
		private boolean color;

		public RBNode() {
		}


		public RBNode<K, V> getParent() {
			return parent;
		}

		public void setParent(RBNode<K, V> parent) {
			this.parent = parent;
		}

		public RBNode<K, V> getLeft() {
			return left;
		}

		public void setLeft(RBNode<K, V> left) {
			this.left = left;
		}

		public RBNode<K, V> getRight() {
			return right;
		}

		public void setRight(RBNode<K, V> right) {
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

		@Override
		public String getPrintInfo() {
			return toString();
		}

		@Override
		public TreeOperation.PrintTreeNode getLeftChild() {
			return left;
		}

		@Override
		public TreeOperation.PrintTreeNode getRightChild() {
			return right;
		}

		@Override
		public String toString() {
			return k + "-" + (color ? "R" : "B");
			//return "[" + k + "]";
		}
	}


	public void inOrderPrint() {
		inOrderPrint(this.root);
	}

	public void inOrderPrint(RBNode<K, V> node) {
		if (node == null) return;
		inOrderPrint(node.left);
		System.out.println("key:" + node.k + " " + "value:" + node.v);
		inOrderPrint(node.right);
	}


	public void insert(K key, V value) {
		RBNode<K, V> node = new RBNode<>();
		node.setK(key);
		node.setV(value);
		node.setColor(RED);
		insert(node);
		insertFixUp(node);
	}

	/**
	 *
	 * 1.红黑树为空插入直接设置成黑色插入即可 处理
	 * 2.插入的key已经存在了替换value值即可 不处理
	 * 3.插入节点的父节点是黑色直接插入即可。 不处理
	 * 4.插入的父节点为红色
	 *		4.1 叔叔节点为红色
	 *			把叔叔节点和父亲节点都染成黑色，把爷爷节点染成红色，递归处理爷爷节点
	 *		4.2 叔叔节点不存在或者为黑色
	 *			4.2.1 叔叔节点不存在或者为黑色，并且插入节点的父节点是爷爷节点的左子树
	 *				4.2.1.1 插入节点是父节点的左子树（LL双红）
	 *				将父亲节点染成黑色，将爷爷节点设置为红色，以爷爷节点进行右旋
	 *				4.2.1.2 插入节点是父节点的右子树（LR）
	 *				以父亲节点进行左旋，会得到LL，指定父亲节点为当前节点进行递归处理。
	 *
	 *			4.2.2 叔叔节点不存在或者为黑色，并且插入节点的父节点是爷爷节点的右子树
	 *				4.2.2.1 插入节点是父节点的左子树（RR双红）
	 *				将父亲节点染成黑色，讲爷爷节点设置成红色，以爷爷节点进行左旋
	 *				4.2.2.2 插入节点是父节点的右子树（RL）
	 *				以父亲节点进行右旋，会得到RR，指定父亲节点为当前节点进行递归。
	 *
	 */
	private void insertFixUp(RBNode<K, V> node) {
		// 直接把头节点设置成黑色
		this.root.setColor(BLACK);
		RBNode<K, V> parent = parentOf(node);
		RBNode<K, V> gParent = parentOf(parent);
		// 父节点不为空并且是红色
		if (parent != null && parent.color) {
			if (gParent != null) {
				// 看下父亲节点是爷爷节点的那个节点
				// 父节点是爷爷节点的左子树
				if (gParent.left == parent) {
					RBNode<K, V> uncle = gParent.right;
					// 叔叔节点是红色
					if (uncle != null && uncle.color) {
						parent.color = BLACK;
						uncle.color = BLACK;
						gParent.color = RED;
						insertFixUp(gParent);
						return;
					}
					//叔叔节点为空或者叔叔节点是黑色,并且父节点是左子树
					// 判断当前插入的节点是父节点的什么节点
					if (node == parent.left) {
						gParent.color = RED;
						parent.color = BLACK;
						rightRotate(gParent);
					} else {
						leftRotate(parent);
						insertFixUp(parent);
					}
				} else { //父亲节点是爷爷节点的右子树
					RBNode<K, V> uncle = gParent.left;
					// 叔叔节点为红色
					if (uncle != null && uncle.color) {
						parent.color = BLACK;
						uncle.color = BLACK;
						gParent.color = RED;
						insertFixUp(gParent);
						return;
					}
					// 叔叔节点为空或者叔叔节点为黑色
					// 判断当前插入的节点是父节点的什么节点
					if (node == parent.right) {
						parent.color = BLACK;
						gParent.color = RED;
						leftRotate(gParent);
					} else {
						leftRotate(parent);
						insertFixUp(parent);
					}
				}

			}

		}


	}


	private RBNode<K, V> parentOf(RBNode<K, V> node) {
		if (node != null) {
			return node.getParent();
		}
		return null;
	}


	private void insert(RBNode<K, V> node) {
		//RBNode<K, V> root = this.root;
		RBNode<K, V> parent = null;
		RBNode<K, V> curSearch = this.root;
		// 这个地方注意一下查找要插入节点的父节点。其实就是
		// 寻找父节点
		while (curSearch != null) {
			// 记录当前查询的父节点
			parent = curSearch;
			int cmp = node.k.compareTo(curSearch.k);
			if (cmp > 0) {
				curSearch = curSearch.right;
			} else if (cmp == 0) {
				curSearch.setV(node.v);
				return;
			} else {
				curSearch = curSearch.left;
			}
		}

		node.parent = parent;

		// 把当前节点设置到父节点中
		if (parent != null) {
			int cmp = node.k.compareTo(parent.k);
			if (cmp > 0) {
				parent.right = node;
			} else {
				parent.left = node;
			}
		} else {
			this.root = node;
		}

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
	public void leftRotate(RBNode<K, V> x) {
		//RBNode left = node.left;
		RBNode<K, V> y = x.right;
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
			this.root.parent = null;
		}
		// 第三步
		x.parent = y;
		y.left = x;
	}


	/**
	 *      P                P
	 *      |				 |
	 *      x				 y
	 *     | \     <----    | \
	 *   lx   y 		   x  ry
	 *       | \		  | \
	 *		ly ry		 lx ly
	 *
	 *	具体过程以节点y进行右旋
	 *
	 */
	public void rightRotate(RBNode<K, V> y) {
		RBNode<K, V> x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}

		if (y.parent != null) {
			x.parent = y.parent;

			if (y.parent.left == y) {
				y.parent.left = x;
			} else {
				y.parent.right = x;
			}
		} else {
			this.root = x;
			this.root.parent = null;
		}
		// 维护x和y的关系
		y.parent = x;
		x.right = y;

	}


}
