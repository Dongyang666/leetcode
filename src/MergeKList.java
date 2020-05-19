import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p/>
 * Description:
 *
 * @author liudongyang
 * @date 2020/04/26
 */
public class MergeKList {
    public static class ListNode {
        private ListNode next;
        private int val;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 优先队列O(N*log(k)) k是链表个数
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLinkedLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(listNode -> listNode.val));
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.add(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            p.next = priorityQueue.poll();
            p = p.next;
            if (p.next != null) {
                priorityQueue.offer(p.next);
            }
        }
        return head.next;
    }

    /**
     * 分治思想
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLinkedLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = ((right - left) >> 1) + left;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
    }
}
