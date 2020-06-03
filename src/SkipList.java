import java.util.Random;

/**
 *
 * https://blog.csdn.net/moakun/article/details/79997037
 * @author liudongyang
 */
public class SkipList<K extends Comparable<K>, V> {

	private Entry<K, V> top;

	private int size;
	private int level;
	private final Random random;

	private static final double PROBABILITY = 0.5;

	public SkipList() {
		random = new Random();
		top = new Entry<>();
		top.down = new Entry<>();
	}

	public void put(K k, V v) {
		// 这个节点可能和插入的节点相同。也可能是刚好小于插入节点的节点
		Entry<K, V> node = findNode(k);
		if (node.k != null && node.k.compareTo(k) == 0) {
			node.v = v;
			return;
		}
		Entry<K, V> insertNode = new Entry<>(k, v);
		// 把插入节点先插入到第0层去
		insertBack(node, insertNode);
		int curLevel = 0;
		while (random.nextDouble() < PROBABILITY) {

			// 如果超过了高度需要新加一层
			if (curLevel >= level) {
				level++;
				// 新的头结点 空头
				Entry<K, V> newLevelHead = new Entry<>();
				/*newLevelHead.right = insertNode;
				insertNode.left = newLevelHead;*/
				top.down.up = newLevelHead;
				newLevelHead.down = top.down;
				top.down = newLevelHead;
			}

			while (node.left != null && node.up == null) {
				node = node.left;
			}
			node = node.up;

			// 上层节点只用保存k就可以了
			Entry<K, V> upInsertNode = new Entry<>(k, null);
			insertBack(node, upInsertNode);
			upInsertNode.down = insertNode;
			insertNode.up = upInsertNode;
			insertNode = upInsertNode;
			curLevel++;
		}
		size++;
	}

	private void insertBack(Entry<K, V> preNode, Entry<K, V> insertNode) {
		if (preNode.right == null) {
			preNode.right = insertNode;
			insertNode.left = preNode;
			return;
		}
		preNode.right.left = insertNode;
		insertNode.right = preNode.right;
		preNode.right = insertNode;
		insertNode.left = preNode;
	}

	private Entry<K, V> findNode(K k) {
		Entry<K, V> p = top;
		while (true) {
			while ((p.right != null && p.right.k.compareTo(k) <= 0)) {
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
	public V get(K k) {
		Entry<K, V> findNode = findNode(k);
		if (k.compareTo(findNode.k) == 0) {
			return findNode.v;
		}
		return null;
	}

	public V remove(K k) {
		if (k == null) return null;
		Entry<K, V> findNode = findNode(k);
		if (k.compareTo(findNode.k) == 0) {
			return deleteNode(findNode);
		}
		return null;
	}

	public V remove(K k, V v) {
		if (k == null) return null;
		Entry<K, V> findNode = findNode(k);
		if (k.compareTo(findNode.k) == 0 && v.equals(findNode.getV())) {
			return deleteNode(findNode);
		}
		return null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		size = 0;
		top = new Entry<>();
		top.down = new Entry<>();
		level = 0;
	}

	private V deleteNode(Entry<K, V> deleteNode) {
		V oldValue = deleteNode.getV();
		while (deleteNode != null) {
			if (deleteNode.right == null) {
				deleteNode.left.right = null;
				deleteNode.left = null;
			} else {
				deleteNode.left.right = deleteNode.right;
				deleteNode.right.left = deleteNode.left;
			}
			deleteNode = deleteNode.up;
		}
		return oldValue;

	}


	public static class Entry<K extends Comparable<K>, V> {
		private Entry<K, V> up = null;
		private Entry<K, V> down = null;
		private Entry<K, V> left = null;
		private Entry<K, V> right = null;

		private K k;
		private V v;

		public Entry() {
		}

		public Entry(K k, V v) {
			this.k = k;
			this.v = v;
		}

		public Entry<K, V> getUp() {
			return up;
		}

		public void setUp(Entry<K, V> up) {
			this.up = up;
		}

		public Entry<K, V> getDown() {
			return down;
		}

		public void setDown(Entry<K, V> down) {
			this.down = down;
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
	}
}
