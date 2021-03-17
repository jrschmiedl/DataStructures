/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract sorter class
 * @author jrschmie Jacob Schmiedl
 * 
 * @param <E> generic data
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Comparator 
	 */
	private Comparator<E> comparator;
	
	/**
	 * Constructor for AbstractComparsionSorter
	 * @param comparator comparator that compares the data
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}
	
	/**
	 * Setter for Comparator
	 * @param comparator comparator that compares the data
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			comparator = new NaturalOrder();
		}
		this.comparator = comparator;
	}

	/**
	 * Inner class NaturalOrder which uses the compare method
	 * @author jrschmie Jacob Schmiedl
	 */
	private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
	
	/**
	 * Compare method that compares two data values
	 * @param data1 first data value
	 * @param data2 second data value
	 * @return integer that is either positive, negative, or 0 which stands 
	 * for whether it is greater, less than, or equal to.
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}
}

