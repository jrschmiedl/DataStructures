package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * Abstract Sorted Map Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> map key
 * @param <V> map value
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
	/** Comparator to compare */
	private Comparator<K> compare;

	/**
	 * Abstract Sorted map Constructor
	 * @param compare comparator
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}
	
	/**
	 * Compare method
	 * @param key1 first key
	 * @param key2 second key
	 * @return int that represents whether key 1 or 2 is larger
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * Natural Order Class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}