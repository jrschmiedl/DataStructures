package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * Abstract Map class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> map key
 * @param <V> map value
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
	
	/**
	 * Map Entry inner class
	 * @author jrschmie Jacob Schmiedl
	 *
	 * @param <K> map key
	 * @param <V> map value
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {
		
		/** Map Key */
		private K key;
		
		/** Map Value */
		private V value;

		/**
		 * Constructor method
		 * @param key map key
		 * @param value map value
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		private void setKey(K key) {
			this.key = key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * Key Iterator inner class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	protected class KeyIterator implements Iterator<K> {
		
		/** Key iterator used */
		private Iterator<Entry<K, V>> it;
		
		/**
		 * Key Iterator Constructor
		 * @param iterator it iterator
		 */
		public KeyIterator(Iterator<Entry<K, V>> iterator) {
			this.it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public K next() {
			return it.next().getKey();
		}
	}
	
	/**
	 * Value Iterator inner class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
		
		/** Value iterator used */
		private Iterator<Entry<K, V>> it;
		
		/**
		 * Value Iterator Constructor
		 * @param iterator it iterator
		 */
		public ValueIterator(Iterator<Entry<K, V>> iterator) {
			this.it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return it.next().getValue();
		}
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}
	
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	/**
	 * Value Iterable inner
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	private class ValueIterable implements Iterable<V> {
		
		/**
		 * Constructor
		 */
		private ValueIterable() {
			//constructor
		}
		
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator(entrySet().iterator());
		}
	}
	
}