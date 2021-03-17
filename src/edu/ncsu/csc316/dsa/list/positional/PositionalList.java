package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * Interface for PositionalList 
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public interface PositionalList<E> extends Iterable<E> {
	/**
	 * Adds a position in the front of the list.
	 * @param p position of the element
	 * @param value E data
	 * @return position of the new node
	 */
	Position<E> addAfter(Position<E> p, E value);
	
	/**
	 * Adds a position before the position given
	 * @param p position of the element
	 * @param value E data
	 * @return position of the new node
	 */
	Position<E> addBefore(Position<E> p, E value);
	
	/**
	 * Adds a position to the front of the list
	 * @param value E data
	 * @return position of the new node
	 */
	Position<E> addFirst(E value);
	
	/**
	 * Adds a position to the end of the list
	 * @param value E data
	 * @return position of the new node
	 */
	Position<E> addLast(E value);
	
	/**
	 * Adds the position to after the list
	 * @param p position of the element
	 * @return position after p
	 */
	Position<E> after(Position<E> p);
	
	/**
	 * Adds the position before the list
	 * @param p position of the element
	 * @return position before p
	 */
	Position<E> before(Position<E> p);
	
	/**
	 * Returns the data at the front of the position
	 * @return first node in the position
	 */
	Position<E> first();
	
	/**
	 * Finds if the list is empty
	 * @return if it is empty
	 */
	boolean isEmpty();
	
	/**
	 * Finds the last position in the list
	 * @return position of the last element in the list
	 */
	Position<E> last();
	
	/**
	 * Returns the position iterable.
	 * @return position iterable created.
	 */
	Iterable<Position<E>> positions();
	
	/**
	 * Removes the element from the position
	 * @param p position of the element
	 * @return value E data
	 */
	E remove(Position<E> p);
	
	/**
	 * Sets the value to the position
	 * @param p position of the element
	 * @param value E data
	 * @return value E data
	 */
	E set(Position<E> p, E value);
	
	/**
	 * Size methods
	 * @return int size
	 */
	int size();
}