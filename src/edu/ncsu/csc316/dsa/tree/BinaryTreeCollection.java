package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * BinaryTreeCollection class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	
	/**
	 * AddRoot method
	 * @param value E
	 * @return position
	 */
    Position<E> addRoot(E value);
    
    /**
     * AddLeft method
     * @param p position
     * @param value E
     * @return p position
     */
    Position<E> addLeft(Position<E> p, E value);
    
    /**
     * AddRight
     * @param p position
     * @param value E
     * @return p position
     */
    Position<E> addRight(Position<E> p, E value);
    
    /**
     * Remove method
     * @param p position
     * @return E
     */
    E remove(Position<E> p);
    
    /**
     * Setter method
     * @param p position
     * @param value E
     * @return E
     */
    E set(Position<E> p, E value);
}