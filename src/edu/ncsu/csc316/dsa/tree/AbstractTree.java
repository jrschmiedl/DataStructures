package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;

/**
 * AbstractTree class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * AbstractNode inner
     * @author jrschmie Jacob Schmiedl
     *
     * @param <E> data
     */
    protected abstract static class AbstractNode<E> implements Position<E> {
    	/** Element */
        private E element;
        
        /**
         * AbstractNode constructor
         * @param element E
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Setter element
         * @param element E
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * ElementIterator inner
     * @author jrschmie Jacob Schmiedl
     *
     */
    protected class ElementIterator implements Iterator<E> {
    	
    	/** Iterator */
        private Iterator<Position<E>> it;
        
        /**
         * ElementIterator Constructor
         * @param iterator used
         */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    } 
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * ToStringHelper method
     * @param sb stringBuilder
     * @param indent string that holds indent
     * @param root position
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
}
