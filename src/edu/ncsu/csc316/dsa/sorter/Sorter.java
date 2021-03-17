
package edu.ncsu.csc316.dsa.sorter;

/**
 * Sorter Interface
 * @author Dr. King
 * 
 * @param <E> generic data
 */
public interface Sorter<E> {
	/**
	 * Sort method that is used on every sorter class
	 * 
	 * @param data that is unsorted
	 */
	void sort(E data[]);
}
