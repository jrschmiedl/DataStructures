package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * BinaryTree class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public interface BinaryTree<E> extends Tree<E> {
	
	/**
	 * Left method
	 * @param p position
	 * @return position
	 */
    Position<E> left(Position<E> p);
    
    /**
     * Right method
     * @param p position
     * @return position
     */
    Position<E> right(Position<E> p);
    
    /**
     * Sibling method
     * @param p position
     * @return position
     */
    Position<E> sibling(Position<E> p);
    
    /**
     * inOrder method
     * @return iterable
     */
    Iterable<Position<E>> inOrder();
}