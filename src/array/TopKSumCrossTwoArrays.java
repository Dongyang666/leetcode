package array;


import java.util.HashSet;
import java.util.Set;

/**
 * @author liudongyang
 * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自 arr1和arr2的两个数相加和最大的前k个，两个数必须分别来自 两个数组。
 * 【举例】 arr1=[1,2,3,4,5]，arr2=[3,5,7,9,11]，k=4。 返回数组[16,15,14,14]。
 */
public class TopKSumCrossTwoArrays {
	static class HeapNode {
		public int row;
		public int col;
		public int value;

		public HeapNode(int row, int col, int value) {
			this.col = col;
			this.row = row;
			this.value = value;
		}
	}

	public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
		topK = Math.min(topK, arr1.length * arr2.length);
		int[] res = new int[topK];
		HeapNode[] heap = new HeapNode[topK + 1];
		int heapSize = 0;
		int headR = arr1.length - 1;
		int headC = arr2.length - 1;
		heapInsert(heap, heapSize++, headR, headC, arr1[headR] + arr2[headC]);
		Set<String> set = new HashSet<>();
		int indexRes = 0;
		while (indexRes != topK) {
			HeapNode head = popHead(heap, heapSize--);
			res[indexRes++] = head.value;
			headR = head.row;
			headC = head.col;
			int uR = headR - 1;
			int uC = headC;
			if (headR != 0 && set.contains(uR + "_" + uC)) {
				heapInsert(heap, heapSize++, uR, uC, arr1[uR] + arr2[uC]);
				set.add(uR + "_" + uC);
			}
			int lR = headR;
			int lC = headC - 1;
			if (headC != 0 && set.contains(lR + "_" + lC)) {
				heapInsert(heap, heapSize++, lR, lC, arr1[lR] + arr2[lC]);
				set.add(lR + "_" + lC);
			}
		}
		return res;

	}

	public static void heapInsert(HeapNode[] heap, int index, int row, int col, int value) {
		heap[index] = new HeapNode(row, col, value);
		int parent = (index - 1) / 2;
		while (index != 0) {
			if (heap[parent].value < heap[index].value) {
				swap(heap, index, parent);
				index = parent;
				parent = (index - 1) / 2;
			} else {
				break;
			}
		}
	}

	public static HeapNode popHead(HeapNode[] heap, int size) {
		HeapNode res = heap[0];
		swap(heap, 0, size);
		heap[size--] = null;
		heapify(heap, 0, size);
		return res;
	}

	/**
	 * 大根堆
	 *
	 * @param heap
	 * @param index
	 * @param size
	 */
	private static void heapify(HeapNode[] heap, int index, int size) {
		int left = index * 2 + 1;
		int right = index * 2 + 1;
		while (left < size) {
			int largest = index;
			if (heap[largest].value < heap[left].value) {
				largest = left;
			}
			if (right < size && heap[largest].value < heap[right].value) {
				largest = right;
			}
			if (largest != index) {
				swap(heap, largest, index);
			} else {
				break;
			}
			index = largest;
			left = largest * 2 + 1;
			right = largest * 2 + 2;
		}
	}

	public static void swap(HeapNode[] heap, int index, int parent) {
		HeapNode tmp = heap[index];
		heap[index] = heap[parent];
		heap[parent] = tmp;
	}

}
