/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Constructor for CountingSorter
	 */
	public CountingSorter() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sort Method that sorts the data using the BubbleSort algorithm
	 * @param data that is unsorted
	 */
	public void sort(E[] data) {
		int min = data[0].getId();
		int max = data[0].getId();
		
		for (int i = 0; i < data.length; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		
		int k = max - min + 1;
		int[] b = new int[k];
		
		for (int i = 0; i < data.length; i++) {
			b[data[i].getId() - min] = b[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i < k; i++) {
			b[i] = b[i - 1] + b[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] f = (E[]) new Identifiable[data.length];
		
		for (int i = 0; i < data.length; i++) {
			f[b[data[i].getId() - min] - 1] = data[i];
			b[data[i].getId() - min] = b[data[i].getId() - min] - 1;
		
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = f[i];
		}
		
		System.out.println(data[0]);
		System.out.println(data[1]);
	}

}
