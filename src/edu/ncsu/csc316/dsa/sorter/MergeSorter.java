package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * MergeSorter class that uses Merge Sort
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor for MergeSorter with a comparator
	 * @param comparator comparator that compares the data
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor for MergeSorter without a comparator
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {
		int length = data.length;
		if (length < 2) {
			//do not need to sort if it is just two
			return;
		}
		
		int middle = (data.length) / 2;
		@SuppressWarnings("unchecked")
		E[] leftData = (E[])(new Comparable[middle]);
		for (int i = 0; i < middle; i++) {
			leftData[i] = data[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] rightData = (E[])(new Comparable[length - middle]);
		for (int i = middle; i < data.length; i++) {
			rightData[i - middle] = data[i];
		}
		
		sort(leftData);
		sort(rightData);
		merge(leftData, rightData, data);
	}
	
	/**
	 * Merges the two arrays of data into one
	 * @param leftData array
	 * @param rightData array
	 * @param data array
	 * @return data array of both left and right combined
	 */
	private E[] merge(E[] leftData, E[] rightData, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		int length = data.length;
		while (leftIndex + rightIndex < length) {
			if (rightIndex == rightData.length || (leftIndex < leftData.length &&
					super.compare(leftData[leftIndex], rightData[rightIndex]) < 0) ) {
				data[leftIndex + rightIndex] = leftData[leftIndex];
				leftIndex++;
			} else {
				data[leftIndex + rightIndex] = rightData[rightIndex];
				rightIndex++;
			}
		}
		return data;
	}

}