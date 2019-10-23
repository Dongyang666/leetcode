package node;

import java.util.HashMap;
import java.util.Map;

/**
 * 克隆随机链表
 *
 * @author liudongyang
 */
public class CloneRandomLinkedList {
	public static class Node {
		public Node next;
		public int val;
		public Node random;

		public Node(int val, Node next, Node random) {
			this.next = next;
			this.val = val;
			this.random = random;
		}
	}

	/**
	 * 使用map实现
	 *
	 * @param head
	 * @return
	 */
	private static Node mapMethod(Node head) {
		Map<Node, Node> cloneNodeMap = new HashMap<Node, Node>();
		Node tmpHead = head;
		//复制到map集合中
		while (tmpHead != null) {
			cloneNodeMap.put(tmpHead, new Node(tmpHead.val, null, null));
			tmpHead = tmpHead.next;
		}
		tmpHead = head;
		while (tmpHead != null) {
			Node curNode = cloneNodeMap.get(tmpHead);
			Node nextNode = cloneNodeMap.get(tmpHead.next);
			Node randomNode = cloneNodeMap.get(tmpHead.random);
			curNode.next = nextNode;
			curNode.random = randomNode;
			tmpHead = tmpHead.next;

		}
		return cloneNodeMap.getOrDefault(head, null);

	}

	/**
	 * @param head
	 * @return
	 */
	public static Node nextNodeMethod(Node head) {
		Node tmpHead = head;
		//copy next
		while (tmpHead != null) {
			Node next = new Node(tmpHead.val, tmpHead.next, null);
			tmpHead.next = next;
			tmpHead = tmpHead.next.next;
		}
		tmpHead = head;
		// cp rondom
		while (tmpHead.next != null) {
			Node curNode = tmpHead.next;
			curNode.random = tmpHead.random == null ? null : tmpHead.random.next;
			tmpHead = tmpHead.next.next;
		}
		tmpHead = head;
		Node resNode = new Node(0, null, null);
		//split
		while (tmpHead.next != null) {
			resNode.next = tmpHead.next;
			resNode = resNode.next;
			tmpHead.next = resNode.next;
		}
		return resNode.next;
	}
}
