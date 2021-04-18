import java.util.HashMap;
import java.util.Map;

/**
 * @author liudongyang
 */
public class LFUCache {

    static class Node {

        int key;
        int value;
        int freq = 1;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }

    }

    Map<Integer, Node> map;
    Map<Integer, DoubleLinkedList> freqMap;
    int capacity;
    int min;
    int size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
        Node node = map.get(key);
        if (node == null) {
            if (size == capacity) {
                DoubleLinkedList minFreqLinkedList = freqMap.get(min);
                map.remove(minFreqLinkedList.tail.pre.key);
                minFreqLinkedList.removeNode(minFreqLinkedList.tail.pre); // 这里不需要维护min, 因为下面add了newNode后min肯定是1.
                size--;
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            DoubleLinkedList linkedList = freqMap.get(1);
            if (linkedList == null) {
                linkedList = new DoubleLinkedList();
                freqMap.put(1, linkedList);
            }
            linkedList.addNodeToHead(newNode);
            min = 1;
            size++;
        } else {
            node.value = value;
            freqInc(node);
        }
    }

    public void freqInc(Node node) {
        int freq = node.freq;
        DoubleLinkedList doubleLinkedList = freqMap.get(freq);
        doubleLinkedList.removeNode(node);
        node.freq++;
        if (freq == min && doubleLinkedList.head.next == doubleLinkedList.tail) {
            min = freq + 1;
        }
        doubleLinkedList = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
        doubleLinkedList.addNodeToHead(node);
        freqMap.put(node.freq, doubleLinkedList);
    }


    public static class DoubleLinkedList {

        private Node head;
        private Node tail;

        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public void addNodeToHead(Node newNode) {
            if (newNode == null) {
                return;
            }
            //前后节点直接用Node不用null代替
            newNode.next = head.next;
            head.next.pre = newNode;
            head.next = newNode;
            newNode.pre = head;

        }

        public void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;

        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity (缓存容量) */);

        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        System.out.println(cache.get(2));

    }

}
