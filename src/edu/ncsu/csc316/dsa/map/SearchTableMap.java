package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Search Table Map Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> map key
 * @param <V> map value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	/** List constant */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructor for SearchTable
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * Constructor with comparator
	 * @param compare comparator
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Key lookup method
	 * @param key map key
	 * @return integer of the key
	 */
	private int lookUp(K key) {
		return binarySearchHelper(0, list.size() - 1, key);
	}

	private int binarySearchHelper(int min, int max, K key) {
		if (min > max) {
			return (min + 1) * -1;
		}
		int middle = (max + min) / 2;
		if (list.get(middle).getKey().equals(key)) {
			return middle;
		} else if (compare(list.get(middle).getKey(), key) > 0) {
			return binarySearchHelper(min, middle - 1, key);
		} else {
			return binarySearchHelper(middle + 1, max, key);
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index < 0) {
			return null;
		}
		V value = list.get(index).getValue();
		return value;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = binarySearchHelper(0, list.size() - 1, key);
		if (index < 0) {
			index = (index + 1) * -1;
			list.add(index, new MapEntry<K, V>(key, value));
			return null;
		} else {
			return list.get(index).setValue(value);
		}
		
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		V value = list.get(index).getValue();
		list.remove(index);
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}