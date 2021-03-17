package edu.ncsu.csc316.dsa.tree;
import edu.ncsu.csc316.dsa.Position;

/**
 * Tree interface
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public interface Tree<E> {
	
	/**
	 * Root method
	 * @return position
	 */
    Position<E> root();
    
    /**
     * Parent method
     * @param p position
     * @return position
     */
    Position<E> parent(Position<E> p);
    
    /**
     * Children method
     * @param p position
     * @return iterable
     */
    Iterable<Position<E>> children(Position<E> p);
    
    /**
     * Number Children method
     * @param p position
     * @return numChildren int
     */
    int numChildren(Position<E> p);
    
    /**
     * isInternal method
     * @param p position
     * @return boolean
     */
    boolean isInternal(Position<E> p);
    
    /**
     * isLeaf method
     * @param p position
     * @return boolean
     */
    boolean isLeaf(Position<E> p);
    
    /**
     * isRoot method
     * @param p position
     * @return boolean
     */
    boolean isRoot(Position<E> p);
    
    /**
     * Size method
     * @return size
     */
    int size();
    
    /**
     * isEmpty method
     * @return boolean
     */
    boolean isEmpty();
    
    /**
     * PreOrder method
     * @return iterable
     */
    Iterable<Position<E>> preOrder();
    
    /**
     * PostOrder method
     * @return iterable
     */
    Iterable<Position<E>> postOrder();
    
    /**
     * LevelOrder method
     * @return iterable
     */
    Iterable<Position<E>> levelOrder();
}