package api;

public class MaxPriorityQueue {

	private int[] array;
	private int heapSize;

	public MaxPriorityQueue(int[] array) {
		this.array = array;
	}

	public int extractMax() {
		int temp = array[0];
		this.array[0] = array[heapSize - 1];
		heapSize--;
		sink(0);
		return temp;
	}
	
	public void increaseKey(int index) {
		
	}

	private int left(int index) {
		return 2 * index + 1;
	}

	private int right(int index) {
		return 2 * index + 2;
	}

	private void sink(int index) {
		int largestIndex = index;
		int left = left(index);
		int right = right(index);
		if (left < heapSize && array[left] > array[largestIndex]) {
			largestIndex = left;
		}
		if (right < heapSize && array[right] > array[largestIndex]) {
			largestIndex = right;
		}
		if (index != largestIndex) {
			swap(largestIndex, index, array);
			sink(largestIndex);
		}
	}

	public int max() {
		return array[0];
	}

	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
