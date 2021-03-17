package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Unordered Array Map Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> map key
 * @param <V> map value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	/** List */
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructor
     */	 
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	// LookUp is a core behavior of all maps
    // This lookup should perform a sequential search
    // and return the index where the entry
    // is located. If the entry is not in the map, return -1
	private int lookUp(K key) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		V value = list.get(index).getValue();
		transpose(index);
		return value;
	}
	
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			list.addFirst(new MapEntry<>(key, value));
			return null;
		} else {
			V v = list.get(index).setValue(value);
			transpose(index);
			return v;
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
	public int size() {
		return list.size();
	}

	/**
	 * Transpose method
	 * @param index index of the value
	 * @return value
	 */
	private V transpose(int index)
	{
		if (index == 0) {
			return null;
		}
		Entry<K, V> temp = list.get(index);
		list.set(index, list.get(index - 1));
		list.set(index - 1, temp);
		return temp.getValue();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> it = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			it.addLast(m);
		}
		return it;
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