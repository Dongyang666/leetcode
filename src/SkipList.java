import java.util.Random;

/**
 *
 * @author liudongyang
 */
public class SkipList<K extends Comparable<K>, V> {

	private SkipNode<K, V> top;

	private int size;
	private int level;
	private Random random;

	private static final double PROBABILITY = 0.5;

	public SkipList() {
		random = new Random();
		top = new SkipNode<>();
	}

	public void put(K k, V v) {
		// 这个节点可能和插入的节点相同。也可能是刚好小于插入节点的节点
		SkipNode<K, V> node = findNode(k);
		if (node.k.compareTo(k) == 0) {
			node.v = v;
			return;
		}
		SkipNode<K, V> insertNode = new SkipNode<>(k, v);
		// 把插入节点先插入到第0层去
		insertBack(node, insertNode);
		int curLevel = 0;
		while (random.nextDouble() < PROBABILITY) {
			// 如果超过了高度需要新加一层
			if (curLevel >= level) {
				level++;
				
			}
		}


	}

	private void insertBack(SkipNode<K, V> preNode, SkipNode<K, V> insertNode) {
		preNode.right.left = insertNode;
		insertNode.right = preNode.right;
		preNode.right = insertNode;
		insertNode.left = preNode;
	}

	private SkipNode<K, V> findNode(K k) {
		SkipNode<K, V> p = top;
		while (true) {
			while (p.right != null && p.right.k.compareTo(k) <= 0) {
				p = p.right;
			}
			if (p.down != null) {
				p = p.down;
			} else {
				break;
			}
		}
		return p;
	}

	/**
	 * 查找
	 * @param k
	 * @return
	 */
	public V search(K k) {
		SkipNode<K, V> findNode = findNode(k);
		if (k.compareTo(findNode.k) == 0) {
			return findNode.v;
		}
		return null;
	}


	public static class SkipNode<K extends Comparable<K>, V> {
		private SkipNode<K, V> up = null;
		private SkipNode<K, V> down = null;
		private SkipNode<K, V> left = null;
		private SkipNode<K, V> right = null;

		private K k;
		private V v;

		public SkipNode() {
		}

		public SkipNode(K k, V v) {
			this.k = k;
			this.v = v;
		}

		public SkipNode<K, V> getUp() {
			return up;
		}

		public void setUp(SkipNode<K, V> up) {
			this.up = up;
		}

		public SkipNode<K, V> getDown() {
			return down;
		}

		public void setDown(SkipNode<K, V> down) {
			this.down = down;
		}

		public SkipNode<K, V> getLeft() {
			return left;
		}

		public void setLeft(SkipNode<K, V> left) {
			this.left = left;
		}

		public SkipNode<K, V> getRight() {
			return right;
		}

		public void setRight(SkipNode<K, V> right) {
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
	}

}
