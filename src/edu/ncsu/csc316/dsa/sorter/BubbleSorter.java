/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter class that uses the BubbleSort algorithm
 * @author Dr. King
 *
 * @param <E> generic data
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor for BubbleSorter with a comparator
	 * @param comparator comparator that compares the data
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor for BubbleSorter without a comparator
	 */
	public BubbleSorter() {
		this(null);
	}

	/**
	 * Sort Method that sorts the data using the BubbleSort algorithm
	 * @param data that is unsorted
	 */
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (super.compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}

}