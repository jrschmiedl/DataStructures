package edu.ncsu.csc316.dsa.queue;

/**
 * Queue.java
 * Interface for Queue
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public interface Queue<E> {
	/**
	 * Adds the value to the end of the queue
	 * @param value E generic data being added
	 */
    void enqueue(E value);
    
    /**
     * Removes the generic data at the front of the queue
     * @return generic data at the front of the queue
     */
    E dequeue();
    
    /**
     * Returns the generic data at the front of the queue
     * @return E generic data at the front of the queue
     */
    E front();
    
    /**
     * Returns the size of the queue
     * @return int of size of the queue
     */
    int size();
    
    /**
     * Returns true or false on whether or not the queue is empty.
     * @return true of false whether the queue is empty or not.
     */
    boolean isEmpty();
}
