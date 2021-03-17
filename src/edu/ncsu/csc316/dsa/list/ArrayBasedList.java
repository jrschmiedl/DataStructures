/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayBasedList class that extends to the AbstractList.
 * @author jrschmie Jacob Schmiedl
 * 
 * @param <E> data 
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/** The default capacity if the capacity is not given */
	private final static int DEFAULT_CAPACITY = 10;
	/** The given data in the array list */
	private E[] data;
	/** The size of the array list */
	private int size;

	/**
	 * Constructor for ArrayBasedList
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Custructor when a capacity is passed through.
	 * @param capacity int the capacity of the array.
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}
	
	@Override
	public void add(int index, E value) {
		ensureCapacity(size + 1);
		checkIndexForAdd(index);
		if (index == size) {
			data[index] = value;
		} else {
			for (int i = size - 1; i >= index; i--) {
				data[i + 1] = data[i];
			}
			data[index] = value;
		}
		size++;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E temp = data[index];
		data[index] = null;
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		data[size - 1] = null;
		size--;
		return temp;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E temp = data[index];
		data[index] = value;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This method makes the array larger when it is at capacity.
	 * @param minCapacity int the minimum capacity of the array.
	 */
	private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * ElementIterator class
	 * @author jrschmie	Jacob Schmiedl
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /** integer that represents the position */
		private int position;
		/** Boolean that is either true or false based on if the remove is okay to do. */
	    private boolean removeOK;

	    public ElementIterator() {
	        position = 0;
	    }

	    public boolean hasNext() {
	        if (position < size) {
	        	return true;
	        }
	        return false;
	    }

	    public E next() {
	        if (position == size) {
	        	throw new NoSuchElementException("e");
	        }
	        removeOK = true;
	        E temp = data[position];
	        position++;
	        return temp;
	    }
	        
	    public void remove() {
	        if (!removeOK) {
	        	throw new IllegalStateException("e");
	        }
	        ArrayBasedList.this.remove(position - 1);
	        position--;
	        removeOK = false;
	    }
	}
}