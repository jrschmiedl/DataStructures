/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

/**
 * List Interface
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public interface List<E> extends Iterable<E> {
	/**
	 * Add method 
	 * @param index int that represents what index the list is at.
	 * @param value E the data
	 */
	void add(int index, E value);
	
	/**
	 * Adds at the beginning of the list
	 * @param value E the data
	 */
    void addFirst(E value);
    
    /**
     * Adds at the very end of the list
     * @param value E the data
     */
    void addLast(E value);
    
    /**
     * Returns the first value in the list
     * @return E the data
     */
    E first();
    
    /**
     * Returns the element that is at that specific index
     * @param index int that represents what index the list is at.
     * @return E the data
     */
    E get(int index);
    
    /**
     * Returns whether or not the list is empty.
     * @return boolean of whether or not the list is empty
     */
    boolean isEmpty();
    
    /**
     * Returns the last element in the list
     * @return E the data
     */
    E last();
    
    /**
     * Removes an element at that specific index
     * @param index int that represents what index the list is at.
     * @return E the data
     */
    E remove(int index);
    
    /**
     * Removes the first element in the list
     * @return E the data
     */
    E removeFirst();
    
    /**
     * Removes the last element in the list
     * @return E the data
     */
    E removeLast();
    
    /**
     * Sets an element to a specific index
     * @param index int that represents what index the list is at.
     * @param value E the data
     * @return E the data
     */
    E set(int index, E value);
    
    /**
     * Size method
     * @return int size
     */
    int size();
}
