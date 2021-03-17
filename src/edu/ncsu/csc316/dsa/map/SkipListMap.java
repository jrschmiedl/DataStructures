package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * SkipListMap Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> list key
 * @param <V> list value
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	/** Random CoinToss */
	private Random coinToss;
	/** Start of the Skip List */
	private SkipListEntry<K, V> start;
	/** Size */
	private int size;
	/** Height */
	private int height;

	/**
	 * Constructor for SkipListMap
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * Constructor with the comparator
	 * @param compare compare used to compare
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	/**
	 * Lookup method
	 * @param key list key
	 * @return current
	 */
	private SkipListEntry<K, V> lookUp(K key) {
		SkipListEntry<K, V> current = start;
		while (current.below != null) {
			current = current.below;
			while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
				current = current.next;
			}
		}
		return current;
	}

	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if (temp.getKey() == null) {
			return null;
		}
		if (compare(temp.getKey(), key) == 0) {
			return temp.getValue();
		}
		return null;
	}
	
	/**
	 * InsertAfterAbove method
	 * @param prev entry
	 * @param down entry
	 * @param key list key
	 * @param value list value
	 * @return insertAfterAbove Entry
	 */
	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		SkipListEntry<K, V> next = new SkipListEntry<K, V>(key, value);
		next.setBelow(down);
		next.setPrevious(prev);
		if (prev != null) {
			next.setNext(prev.next);
			next.prev.setNext(next);
		}
		if (next.next != null) {
			next.next.setPrevious(next);
		}
		if (down != null) {
			down.setAbove(next);
		}
		return next;
	}

	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> temp = lookUp(key);
		K newKey = temp.getKey();
		if (newKey != null && compare(key, newKey) == 0) {
			V oldValue = temp.getValue();
			while (temp != null) {
				temp.setValue(value);
				temp = temp.getAbove();
			}
			return oldValue;
		}
		
		SkipListEntry<K, V> newTemp = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			if (currentLevel >= height) {
				height++;
				SkipListEntry<K, V> tail = start.next;
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}
			newTemp = insertAfterAbove(temp, newTemp, key, value);
			while (temp.getAbove() == null) {
				temp = temp.prev;
			}
			temp = temp.getAbove();
		} while (coinToss.nextBoolean());
		size++;
		return null;
	}
	
    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        if (isSentinel(temp)) {
        	return null;
        }
        V value = temp.getValue();
        while (temp != null) {
        	temp.getPrevious().setNext(temp.getNext());
        	temp.getNext().setPrevious(temp.getPrevious());
        	temp = temp.getAbove();
        }
        size--;
        return value;
    }	

	@Override
	public int size() {
		return size;
	}
	
	@Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.below;
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");
		
		return sb.toString();
	}

//    // This method may be useful for testing or debugging.
//    // You may comment-out this method instead of testing it, since
//    // the full string will depend on the series of random coin flips
//    // and will not have deterministic expected results.	
//	public String toFullString() {
//		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
//		SkipListEntry<K,V> cursor = start;
//		SkipListEntry<K,V> firstInList = start;
//		while( cursor != null) {
//			firstInList = cursor;
//			sb.append("-INF -> ");
//			cursor = cursor.next;
//			while(cursor != null && !isSentinel(cursor)) {
//				sb.append(cursor.getKey()+ " -> ");
//				cursor = cursor.next;
//			}
//			sb.append("+INF\n");
//			cursor = firstInList.below;
//		}
//		sb.append("]");
//		return sb.toString();
//	}
	
	/**
	 * SkipListEntry inner
	 * @author jrschmie Jacob Schmiedl
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {
		
		/** Next */
		private SkipListEntry<K, V> next;
		
		/** Previous */
		private SkipListEntry<K, V> prev;
		
		/** Above */
		private SkipListEntry<K, V> above;
		
		/** Below */
		private SkipListEntry<K, V> below;
		
		/**
		 * SkipListEntry constructor
		 * @param key key
		 * @param value value
		 */
		public SkipListEntry(K key, V value) {
			super(key, value);
			setAbove(null);
			setBelow(null);
			setPrevious(null);
			setNext(null);
		}

		/**
		 * Setter method
		 * @param next SkipListEntry
		 */
		private void setNext(SkipListEntry<K, V> next) {
			this.next = next;
		}

		/**
		 * Setter method
		 * @param prev SkipListEntry
		 */
		private void setPrevious(SkipListEntry<K, V> prev) {
			this.prev = prev;
		}

		/**
		 * Setter method
		 * @param down SkipListEntry
		 */
		private void setBelow(SkipListEntry<K, V> down) {
			this.below = down;
		}

		/**
		 * Setter method
		 * @param up SkipListEntry
		 */
		private void setAbove(SkipListEntry<K, V> up) {
			this.above = up;
		}
		
		/**
		 * Getter for Next
		 * @return next
		 */
		public SkipListEntry<K, V> getNext() {
			return next;
		}
		
		/**
		 * Getter for Previous
		 * @return prev
		 */
		public SkipListEntry<K, V> getPrevious() {
			return prev;
		}
		
		/**
		 * Getter for Above
		 * @return above
		 */
		public SkipListEntry<K, V> getAbove() {
			return above;
		}
	}
}
