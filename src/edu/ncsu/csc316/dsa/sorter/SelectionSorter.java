package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor for SelectionSorter with a comparator
	 * @param comparator comparator that compares the data
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor for SelectionSorter without a comparator
	 */
	public SelectionSorter() {
		this(null);
	}
	
	/**
	 * Sort Method that sorts the data using the SelectionSort algorithm
	 * @param data that is unsorted
	 */
	public void sort(E[] data) {
		for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (super.compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if (i != min) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}
	}
}