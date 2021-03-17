/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter class that uses the InsertionSort algorithm
 * @author Dr. King
 * 
 * @param <E> generic data
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	//private Comparator<E> comparator;
	
	/**
	 * Constructor for InsertionSorter with a comparator
	 * @param comparator comparator that compares the data
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructor for InsertionSorter without a comparator
	 */
	public InsertionSorter() {
		super(null);
	}

	/**
	 * Sort Method that sorts the data using the InsertionSort algorithm
	 * @param data that is unsorted
	 */
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}	
	}
}
