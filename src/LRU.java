import java.util.HashMap;

/**
 * LRU算法 最近最常使用算法，内存加载
 */
public class LRU {


	public static class Node<V> {
		public Node<V> pre;
		public Node<V> next;
		public V value;

		public Node(V value) {
			this.next = next;
			this.pre = pre;
			this.value = value;
		}
	}

	public static class MyCache<K, V> {
		//map可保证为（1）的查询对应关系
		private HashMap<K, Node<V>> keyNodeMap;
		private NodeDoubleLinkedList<V> nodeList;
		private int capacity;


		public MyCache(int capacity) {
			if (capacity < 1) {
				new RuntimeException("should be more than 1 ");
			}
			this.capacity = capacity;
			this.keyNodeMap = new HashMap<>();
			this.nodeList = new NodeDoubleLinkedList<>();
		}

		public V get(K key) {
			if (this.keyNodeMap.containsKey(key)) {
				Node<V> res = this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		public void set(K key, V value) {
			if (keyNodeMap.containsKey(key)) {
				Node<V> res = this.keyNodeMap.get(key);
				res.value = value;
				this.nodeList.moveNodeToTail(res);
			} else {
				Node<V> res = new Node<>(value);
				this.nodeList.addNode(res);
				this.keyNodeMap.put(key, res);
				//大于指定容量移除掉头部，为最不经常使用的节点，同时移除掉map集合中的元素
				if (this.keyNodeMap.size() == capacity + 1) {
					Node<V> removeNode = this.nodeList.removeHead();
					keyNodeMap.remove(removeNode.value);
				}
			}
		}
	}

	/**
	 * 双向链表 ，有头尾指针，头指针为最不经常使用的节点，尾指针为刚使用完的节点
	 *
	 * @param <V>
	 */
	public static class NodeDoubleLinkedList<V> {
		private Node<V> head;
		private Node<V> tail;

		public NodeDoubleLinkedList() {
			this.head = null;
			this.tail = null;
		}

		public void addNode(Node<V> newNode) {
			if (newNode == null) {
				return;
			}
			if (this.head == null) {
				this.head = newNode;
				this.tail = newNode;
			} else {
				//双向链表
				this.tail.next = newNode;
				newNode.pre = this.tail;
				this.tail = newNode;
			}

		}

		public void moveNodeToTail(Node<V> node) {
			if (this.tail == null) {
				return;
			}
			//先断开之前的位置
			if (this.head == node) {
				head = node.next;
				this.head.pre = null;
			} else {
				node.pre.next = node.next;
				node.next.pre = node.pre;
			}
			//移动到最后的位置
			node.pre = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}

		public Node<V> removeHead() {
			if (head == null) {
				return null;
			}
			Node removeNode = this.head;
			if (this.head == this.tail) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = removeNode.next;
				this.head.pre = null;
				removeNode.next = null;
			}
			return removeNode;
		}
	}
}
