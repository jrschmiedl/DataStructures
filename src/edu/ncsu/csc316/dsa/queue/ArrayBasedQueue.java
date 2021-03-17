package edu.ncsu.csc316.dsa.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * ArrayBasedQueue.java takes in new data and then removes it from the array
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {
	/** The data in the queue */
	private E[] data;
	/** front of the queue */
	private int front;
	/** The size of the queue */
	private int size;
	
	/** Default Capacity of the Queue */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Constructor with a specific capacity
	 * @param initialCapacity int the starting capacity of the queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity)
	{
		data = (E[])(new Object[initialCapacity]);
		size = 0;
	}
	
	/**
	 * Constructor for the ArrayBasedQueue
	 */
	public ArrayBasedQueue()
	{
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(E value) {
		ensureCapacity(size + 1);
		if (size == data.length) {
			throw new IllegalStateException("full");
		}
		data[size] = value;
		size++;
	}

	@Override
	public E dequeue() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E temp = data[front];
		data[front] = null;
		for (int i = 0; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return temp;
	}

	@Override
	public E front() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return data[front];
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
}