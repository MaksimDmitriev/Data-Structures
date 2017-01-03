package api;

import java.util.Arrays;

public class HeapSort {

	private int[] array;
	private int heapSize;

	public HeapSort(int[] array) {
		this.array = array;
		this.heapSize = array.length;
	}

	public void sort() {
		buildMaxHeap();
		for (int i = heapSize - 1; i >= 0; i--) {
			swap(0, i, array);
			heapSize--;
			sink(0);
		}
		heapSize = array.length;
	}

	private void buildMaxHeap() {
		for (int i = heapSize / 2 - 1; i >= 0; i--) {
			sink(i);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
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

	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
