package edu.ncsu.csc316.dsa.queue;

/**
 * Abstract class for Queue
 * The class tells the queue if it is empty or not.
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}