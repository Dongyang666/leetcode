import java.util.PriorityQueue;

/**
 * @author liudongyang
 * @date 2020/06/18
 */
public class MedianFinder {
    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> maxHeap = null;
    PriorityQueue<Integer> minHeap = null;

    public MedianFinder() {
        // 从大到小
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        // 从小到大
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    }

    public void addNum(int num) {
        if (maxHeap.size() > minHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }

    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.size();
        }
        return (double) (maxHeap.peek() + minHeap.peek()) / 2;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
    }
}
