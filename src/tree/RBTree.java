package tree;

/**
 * @author liudongyang
 * 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Entry<K, V> root;

	private int size;

	public Entry<K, V> getRoot() {
		return root;
	}

	public int size() {
		return size;
	}


	public boolean isEmpty() {
		return root == null;
	}

	public boolean containsKey(K key) {
		return get(key) == null;
	}

	public void clear() {
		root = null;
	}

	public static class Entry<K extends Comparable<K>, V> implements TreeOperation.PrintTreeNode {
		private Entry<K, V> parent;
		private Entry<K, V> left;
		private Entry<K, V> right;
		private K k;
		private V v;
		private boolean color;

		public Entry() {
		}


		public Entry<K, V> getParent() {
			return parent;
		}

		public void setParent(Entry<K, V> parent) {
			this.parent = parent;
		}

		public Entry<K, V> getLeft() {
			return left;
		}

		public void setLeft(Entry<K, V> left) {
			this.left = left;
		}

		public Entry<K, V> getRight() {
			return right;
		}

		public void setRight(Entry<K, V> right) {
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

	public void inOrderPrint(Entry<K, V> node) {
		if (node == null) {
			return;
		}
		inOrderPrint(node.left);
		System.out.println("key:" + node.k + " " + "value:" + node.v);
		inOrderPrint(node.right);
	}


	public void put(K key, V value) {
		Entry<K, V> node = new Entry<>();
		node.setK(key);
		node.setV(value);
		node.setColor(RED);
		insert(node);
		insertFixUp(node);
	}


	public V get(K key) {
		Entry<K, V> curSearch = root;
		while (curSearch != null) {
			int cmp = key.compareTo(curSearch.k);
			if (cmp > 0) {
				curSearch = curSearch.right;
			} else if (cmp == 0) {
				return curSearch.v;
			} else {
				curSearch = curSearch.left;
			}
		}
		return null;
	}

	/**
	 * 1.红黑树为空插入直接设置成黑色插入即可 处理
	 * 2.插入的key已经存在了替换value值即可 不处理
	 * 3.插入节点的父节点是黑色直接插入即可。 不处理
	 * 4.插入的父节点为红色
	 * 4.1 叔叔节点为红色
	 * 把叔叔节点和父亲节点都染成黑色，把爷爷节点染成红色，递归处理爷爷节点
	 * 4.2 叔叔节点不存在或者为黑色
	 * 4.2.1 叔叔节点不存在或者为黑色，并且插入节点的父节点是爷爷节点的左子树
	 * 4.2.1.1 插入节点是父节点的左子树（LL双红）
	 * 将父亲节点染成黑色，将爷爷节点设置为红色，以爷爷节点进行右旋
	 * 4.2.1.2 插入节点是父节点的右子树（LR）
	 * 以父亲节点进行左旋，会得到LL，指定父亲节点为当前节点进行递归处理。
	 *
	 * 4.2.2 叔叔节点不存在或者为黑色，并且插入节点的父节点是爷爷节点的右子树
	 * 4.2.2.1 插入节点是父节点的左子树（RR双红）
	 * 将父亲节点染成黑色，讲爷爷节点设置成红色，以爷爷节点进行左旋
	 * 4.2.2.2 插入节点是父节点的右子树（RL）
	 * 以父亲节点进行右旋，会得到RR，指定父亲节点为当前节点进行递归。
	 */
	private void insertFixUp(Entry<K, V> node) {
		// 直接把头节点设置成黑色
		this.root.setColor(BLACK);
		Entry<K, V> parent = parentOf(node);
		Entry<K, V> gParent = parentOf(parent);
		// 父节点不为空并且是红色
		if (parent != null && parent.color) {
			// 如果父节点是红色的话 爷爷肯定不是空
			// 看下父亲节点是爷爷节点的那个节点
			// 父节点是爷爷节点的左子树
			if (gParent.left == parent) {
				Entry<K, V> uncle = gParent.right;
				// 叔叔节点是红色
				if (uncle != null && uncle.color) {
					parent.color = BLACK;
					uncle.color = BLACK;
					gParent.color = RED;
					insertFixUp(gParent);
					// 这个地方需要return掉
					return;
				}
				//叔叔节点为空或者叔叔节点是黑色,并且父节点是左子树
				// 判断当前插入的节点是父节点的什么节点
				if (node == parent.left) {
					gParent.color = RED;
					parent.color = BLACK;
					rightRotate(gParent);
					//LR的情况，先左旋父亲节点变成LL双红 然后就当父亲节点是插入节点递归处理
				} else {
					leftRotate(parent);
					insertFixUp(parent);
				}
			} else { //父亲节点是爷爷节点的右子树
				Entry<K, V> uncle = gParent.left;
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


	private Entry<K, V> parentOf(Entry<K, V> node) {
		if (node != null) {
			return node.getParent();
		}
		return null;
	}


	private void insert(Entry<K, V> node) {
		Entry<K, V> parent = null;
		Entry<K, V> curSearch = this.root;
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
		++size;

	}


	/**
	 * P                P
	 * |				 |
	 * x				 y
	 * | \     ---->    | \
	 * lx   y 		   x  ry
	 * | \		  | \
	 * ly ry		 lx ly
	 *
	 * 具体过程
	 * 1.先解决y的左子树
	 * 将y的左子树的节点的父节点指向x，并将x的右子树指向y的左子树（ly）。
	 * 2.把y提升上去
	 * 如果x节点的父节点不为空，把y的父节点指向x的父节点（P），并把P节点指向y(需要知道x是P的左节点还是右节点)
	 * 3.维护x、y的父子关系
	 * 把x的父节点设置为y，把y的左子树设置为x
	 */
	private void leftRotate(Entry<K, V> x) {
		//RBNode left = node.left;
		Entry<K, V> y = x.right;
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
	 * P                P
	 * |				 |
	 * x				 y
	 * | \     <----    | \
	 * lx   y 		   x  ry
	 * | \		  | \
	 * ly ry		 lx ly
	 *
	 * 具体过程以节点y进行右旋
	 */
	private void rightRotate(Entry<K, V> y) {
		Entry<K, V> x = y.left;
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


	public V remove(K k) {
		V oldValue = null;
		Entry<K, V> curSearch = root;
		while (curSearch != null) {
			int cmp = k.compareTo(curSearch.k);
			if (cmp > 0) {
				curSearch = curSearch.right;
			} else if (cmp == 0) {
				oldValue = curSearch.v;
				deleteNode(curSearch);
				--size;
				return oldValue;
			} else {
				curSearch = curSearch.left;
			}
		}
		return oldValue;
	}

	/**
	 * 删除节点
	 * case1 删除的节点的左子树和右子树都不为空。则找见这个被删除节点的后继节点（右子树最左节点）然后替换当前删除节点。删除后继节点
	 *
	 *
	 * case2 删除的节点只有一个节点。那么被删除的节点一定是个黑色节点。而且他的儿子一定是红孩儿。红色节点不可能只有一条子树
	 * （为甚？假设红色节点有一个儿子。那个这个儿子一定是黑色的（红红不能相连）那么左右子树肯定不平衡）所以只能是黑色节点。
	 * 直接用红儿子把当前删除节点替换掉，并且染成黑色。
	 *
	 * case3 删除节点是一个叶子节点。这种情况比较多
	 * 		3.1 删除的叶子节点是一个红色节点。直接删除即可不会影响平衡
	 * 		3.2 删除的叶子节点是一个黑色节点。可以分为兄弟节点为红色或者黑色
	 * 			3.2.1 删除节点的兄弟节点是黑色节点，又可以分为父亲节点是红色还是黑色。还有兄弟节点儿子红黑的情况等等。
	 * 				3.2.1.1 兄弟节点没有儿子的情况，
	 * 					3.2.1.1.1 删除节点的父亲节点是红色，这种情况下删除了左边的树少了一个黑色节点。并且兄弟没有儿子。
	 * 					直接把老父亲染成黑色然后把兄弟节点染成红色就平衡了。
	 * 					3.2.1.1.2 删除节点的父亲节点是黑色的。这种情况下删除了左边的节点之后 父亲和兄弟有黑色只能先把兄弟染成
	 * 					红色，然后以父亲节点继续调整。
	 * 			   3.2.1.2 兄弟节点有儿子的情况。兄弟节点的左儿子为红色。想办法把这个红色节点弄上去替换删除节点的父亲节点。
	 *						把父亲节点染黑弄到左边去补上少了的黑色节点。
	 *						这样先以兄弟节点右旋一下。再以删除节点的父亲节点左旋。把转上去的红色侄子节点染成删除节点父亲节点的颜色
	 *						。然后把旋到左边的父亲节点染成黑色。这样左右就平衡了。
	 *				3.2.1.3 兄弟节点有儿子，并且兄弟节点的右儿子为红色节点。把兄弟染成父亲节点的颜色来替换父亲节点。
	 *						然后把父亲节点染成黑色放到左边来替代被删除的黑色节点
	 *						先把兄弟节点染成父亲节点的颜色。然后父亲节点左旋并且把父亲染成黑色
	 *
	 * 			3.2.2 删除的节点的兄弟节点是一个红色的节点。（两个儿子肯定是黑色的）可以转成兄弟节点为黑色。
	 * 					先以父亲节点左旋一下，把父亲节点弄到左边并且染成红色。然后把旋到上面的红色的兄弟节点染成黑色。
	 *
	 * 			其他情况都是对称处理下
	 *
	 * @param node
	 */
	private void deleteNode(Entry<K, V> node) {
		// 表示删除之后替换上来的节点
		Entry<K, V> replace = null;
		// 删除节点的父亲节点
		Entry<K, V> parent = null;

		//
		if (node.left != null && node.right != null) {
			// 找后继节点替换删除
			Entry<K, V> nodeRightMostLeft = node.right;
			// 找到右节点的最左节点
			while (nodeRightMostLeft.left != null) {
				nodeRightMostLeft = nodeRightMostLeft.left;
			}
			// 替换当前节点
			node.k = nodeRightMostLeft.k;
			// 把被替换的节点当成删除节点 递归处理
			deleteNode(nodeRightMostLeft);
			// 叶子节点或者只有一个节点
		} else {
			// 先计算下要被替换的节点
			Entry<K, V> child = node.left == null ? node.right : node.left;
			// 这个子节点就是被替换的节点
			replace = child;
			// 删除的是根节点
			if (node.parent == null) {
				// 把子节点提上来
				this.root = child;
				// 并且把新提上来的节点的父节点设置为空
				this.root.parent = null;
				// 删除非根的情况
			} else {
				// 看下要被替换的节点是父节点左子树还是右子树,并把父节点指向子节点
				if (node.parent.left == node) {
					node.parent.left = child;
				} else {
					node.parent.right = child;
				}
				// 把两个儿子是空的都过滤掉
				if (child != null) {
					child.parent = node.parent;
				}
				// 记录下删除节点的父节点
				parent = node.parent;

			}
			// 如果被替换的节点是红色直接可以结束。如果是黑色则需要调整
			if (node.color == BLACK) {
				deleteFixUp(replace, parent);
			}


		}


	}

	/**
	 * 删除黑色节点之后的调整删除
	 *
	 * @param replace 被替换的节点
	 * @param parent  被替换的节点的父节点
	 */
	private void deleteFixUp(Entry<K, V> replace, Entry<K, V> parent) {
		Entry<K, V> brother;
		// 处理被删除节点是黑节点的情况
		while ((replace == null || replace.color == BLACK) && replace != this.root) {
			// 被替换节点分为左子树和右子树
			// 被删除节点是他爸爸的左子树
			if (parent.left == replace) {
				// 先拿被删除节点的兄弟节点
				brother = parent.right;
				// 如果兄弟节点是红色的,需要把红兄弟变成黑兄弟在处理
				if (brother.color) {
					// 先把父亲左旋一下
					leftRotate(parent);
					parent.color = RED;
					brother.color = BLACK;
					// 兄弟节点变成黑兄的情况了
					brother = parent.right;
				}

				//下来处理黑兄弟的情况，黑兄弟的情况比较依赖黑兄弟的两个儿子的颜色
				// case1 兄嘚节点的两个孩子都是黑色
				if ((brother.left == null || brother.left.color == BLACK) &&
						(brother.right == null || brother.right.color == BLACK)) {

					// 左边少了和黑色并且父亲还是个红色直接把父亲改成黑色的 把兄嘚节点改成红色就行了
					if (parent.color == RED) {
						parent.color = BLACK;
						brother.color = RED;
						break;
						// 老父亲也是黑色的节点 兄嘚也是黑色的 这样删除了左边就少了一个黑色的节点 需要把兄嘚改成红色接着调整
					} else {
						brother.color = RED;
						replace = parent;
						parent = replace.parent;
					}

				} else {
					// case3 黑兄，兄弟的左孩子为红色 因为红色的节点删除不影响整个红黑树性质，所以想办法利用兄嘚的
					// 左红孩儿干一些事情，
					// 先把兄嘚右旋一下。让红色节点上去
					// 然后再把红色节点染成parent节点的颜色。吧parent左旋一下。
					// 再把parent染成黑色就完成平衡了
					if (brother.left != null && brother.left.color == RED) {
						rightRotate(brother);
						brother.parent.color = parent.color;
						leftRotate(parent);
						parent.color = BLACK;
						// case4 黑兄，兄弟的右孩子为红色 因为红色的节点删除不影响整个红黑树性质，所以想办法利用兄嘚的
						// 右红孩儿干些事情 其实就是用兄弟去顶替父亲节点 吧父亲节点变成黑色去填补左边的空缺
						// 先把兄嘚染成父亲节点的颜色
						//
					} else if (brother.right != null && brother.right.color == RED) {
						brother.color = parent.color;
						leftRotate(parent);
						parent.color = BLACK;
						brother.right.color = BLACK;

					}
					break;
				}
				// 被删除节点是他爸爸的右子树 ,和左子树处理方式一样 变一下方向
			} else {
				brother = parent.left;

				// 兄嘚是红色了 需要把兄嘚转成黑色 其实就是把红得的红色转到自己这边来
				if (brother.color) {
					// 先把老父亲右旋一下
					rightRotate(parent);
					brother.color = BLACK;
					parent.color = RED;
					// 维护下关系
					brother.left = parent;

				}
				// 兄嘚现在变成黑色了，
				// 假如两个兄弟的儿子都不存在或者都是黑的
				if ((brother.left == null || brother.left.color == BLACK)
						&& (brother.right == null || brother.right.color == BLACK)) {
					// 如果老父亲是红色
					if (parent.color == RED) {
						// 把老父亲染成黑色 把兄嘚染成红色就行了
						parent.color = BLACK;
						brother.color = RED;

						// 如果老父亲是黑色
					} else {
						// 这种情况下全黑了没有红色给调换删掉的空缺只能重新调整
						brother.color = RED;
						replace = parent;
						parent = replace.parent;
					}
				} else {
					// case3 黑兄，兄弟的左孩子为红色，右孩子随意
					if (brother.left != null && brother.left.color == RED) {
						//先把brother弄上去，然后把parent节点弄成黑色 再把留在左边的兄嘚的左儿子变成黑色就平衡了
						brother.color = parent.color;
						parent.color = BLACK;
						rightRotate(parent);
						brother.left.color = BLACK;
					} else if (brother.right != null && brother.right.color == RED) {
						//这种则是要把黑兄嘚的孩子弄上去当爸爸 把爸爸弄右边去充当黑色的苦力
						brother.right.color = parent.color;
						leftRotate(brother);
						parent.color = BLACK;
						rightRotate(parent);
					}
					break;
				}

			}
		}

		// 把被替换的节点颜色设置成黑色即可
		if (replace != null) {
			replace.color = BLACK;
		}
	}


}
