package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * GeneralTreeCollection Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	
	/**
	 * AddRoot method
	 * @param value E
	 * @return position
	 */
    Position<E> addRoot(E value);
    
    /**
     * AddChild method
     * @param p position
     * @param value E
     * @return position
     */
    Position<E> addChild(Position<E> p, E value);
    
    /**
     * Remove method
     * @param p position
     * @return E value
     */
    E remove(Position<E> p);
    
    /**
     * Setter method
     * @param p position
     * @param value E
     * @return E value
     */
    E set(Position<E> p, E value);
}