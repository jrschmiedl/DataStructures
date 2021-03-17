/**
 * 
 */
package edu.ncsu.csc316.dsa.map;

/**
 * Map Interface Class
 * @author jrschmie Jacob Schmiedl
 * @param <K> map key
 * @param <V> map value
 */
public interface Map<K, V> extends Iterable<K> {
	
	/**
	 * Returns the Iterable entry set
	 * @return entries
	 */
	Iterable<Entry<K, V>> entrySet();
	
	/**
	 * Getter for map value
	 * @param key map key
	 * @return map value associated with the map key
	 */
	V get(K key);
	
	/**
	 * Returns whether or not the map is empty
	 * @return boolean
	 */
	boolean isEmpty();
	
	/**
	 * Inserts the value at the given key
	 * @param key map key
	 * @param value map value
	 * @return value
	 */
	V put(K key, V value);
	
	/**
	 * Removes the value associated at the key
	 * @param key map key
	 * @return value
	 */
	V remove(K key);
	
	/**
	 * Getter for size of the map
	 * @return size
	 */
	int size();
	
	/**
	 * Iterable for Values
	 * @return Iterable of value
	 */
	Iterable<V> values();
	
	/**
	 * Entry interface
	 * @author jrschmie Jacob Schmiedl
	 *
	 * @param <K> map key
	 * @param <V> map value
	 */
	interface Entry<K, V> {
		
		/**
		 * Getter for the key
		 * @return key
		 */
		K getKey();
		
		/**
		 * Getter for the value
		 * @return value
		 */
		V getValue();
		
		/**
		 * Setter for value
		 * @param value map value
		 * @return value
		 */
		V setValue(V value);
	}
}

