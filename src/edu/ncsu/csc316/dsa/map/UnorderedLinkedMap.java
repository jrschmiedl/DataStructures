package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Unordered Linked Map class 
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> map key
 * @param <V> map value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/** List */
	private PositionalList<Entry<K, V>> list;
	
	/**
	 * Constructor
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	/**
	 * Lookup Method
	 * @param key map key
	 * @return position
	 */
	private Position<Entry<K, V>> lookUp(K key)
	{
		Position<Entry<K, V>> temp = list.first();
		for (int i = 0; i < list.size(); i++) {
			if (temp.getElement().getKey().equals(key)) {
				return temp;
			}
			temp = list.after(temp);
		}
		return null;
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			return null;
		}
		V value = p.getElement().getValue();
		moveToFront(p);
		return value;
	}
	
	/**
	 * Move to The front
	 * @param position position
	 */
	private void moveToFront(Position<Entry<K, V>> position) {
		list.addFirst(position.getElement());
		list.remove(position);
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			list.addFirst(new MapEntry<>(key, value));
			return null;
		} else {
			V temp = list.set(p, new MapEntry<K, V>(key, value)).getValue();
			moveToFront(p);
			return temp;
		}
	}
	
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       }
       return list.remove(p).getValue();
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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