package api;

import java.util.ArrayList;
import java.util.List;

public class LinearTimeSorts {

	// http://www.opendatastructures.org/ods-java/11_2_Counting_Sort_Radix_So.html
	public static int[] countingSort(int[] a, int k) {
		int c[] = new int[k];
		for (int i = 0; i < a.length; i++)
			c[a[i]]++;
		for (int i = 1; i < k; i++)
			c[i] += c[i - 1];
		int b[] = new int[a.length];
		for (int i = a.length - 1; i >= 0; i--)
			b[--c[a[i]]] = a[i];
		return b;
	}

	// http://www.geekviewpoint.com/java/sorting/radixsort
	public static void radixsort(int[] input) {
		final int RADIX = 10;
		// declare and initialize bucket[]
		List<Integer>[] bucket = new ArrayList[RADIX];
		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = new ArrayList<Integer>();
		}

		// sort
		boolean maxLength = false;
		int tmp = -1, placement = 1;
		while (!maxLength) {
			maxLength = true;
			// split input between lists
			for (Integer i : input) {
				tmp = i / placement;
				bucket[tmp % RADIX].add(i);
				if (maxLength && tmp > 0) {
					maxLength = false;
				}
			}
			// empty lists into input array
			int a = 0;
			for (int b = 0; b < RADIX; b++) {
				for (Integer i : bucket[b]) {
					input[a++] = i;
				}
				bucket[b].clear();
			}
			// move to next digit
			placement *= RADIX;
		}
	}

	// http://www.growingwiththeweb.com/2015/06/bucket-sort.html
	public static void bucketSort(Integer[] array, int bucketSize) {
		if (array.length == 0) {
			return;
		}

		// Determine minimum and maximum values
		Integer minValue = array[0];
		Integer maxValue = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
			} else if (array[i] > maxValue) {
				maxValue = array[i];
			}
		}

		// Initialise buckets
		int bucketCount = (maxValue - minValue) / bucketSize + 1;
		List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new ArrayList<Integer>());
		}

		// Distribute input array values into buckets
		for (int i = 0; i < array.length; i++) {
			buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
		}

		// Sort buckets and place back into input array
		int currentIndex = 0;
		for (int i = 0; i < buckets.size(); i++) {
			Integer[] bucketArray = new Integer[buckets.get(i).size()];
			bucketArray = buckets.get(i).toArray(bucketArray);
			insertionSort(bucketArray);
			for (int j = 0; j < bucketArray.length; j++) {
				array[currentIndex++] = bucketArray[j];
			}
		}
	}

	public static void insertionSort(Integer[] array) {
		for (int i = 1; i < array.length; i++) {
			int j = i;
			int temp = array[j--];
			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
	}
}