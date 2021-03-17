package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * PositionalLinkedList class which has 
 * @author 19195
 *
 * @param <E> data
 */
public class PositionalLinkedList<E> implements PositionalList<E> {
	/** The front positional node */
	private PositionalNode<E> front;
	/** the tail positional node */
	private PositionalNode<E> tail;
	/** the size of the LinkedList */
	private int size;

	/**
	 * Constructor for PositionalLinkedList
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	@Override
    public Iterator<E> iterator() {
        return new ElementIterator(front.getNext());
    }

	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		return addBetween(value, n.getNext(), n);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		return addBetween(value, n, n.getPrevious());
	}

	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}
	
	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> latest = new PositionalNode<E>(value, next, prev);
        prev.setNext(latest);
        next.setPrevious(latest);
        size++;
        return latest;
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> n = validate(p);
		return position(n.getNext());
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> n = validate(p);
		return position(n.getPrevious());
	}

	@Override
	public Position<E> first() {
		return position(front.getNext());
	}
	
	
	private PositionalNode<E> position(PositionalNode<E> n) {
		if(n == front || n == tail) {
			return null;
		}
		return n;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		return position(tail.getPrevious());
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> n = validate(p);
		PositionalNode<E> prev = n.getPrevious();
		PositionalNode<E> next = n.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
		E temp = n.getElement();
		n.setElement(null);
		n.setNext(null);
		n.setPrevious(null);
		return temp;
		
	}

	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> n = validate(p);
		E temp = n.getElement();
		n.setElement(value);
		return temp;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

	
	private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	/**
	 * PositionalNode Class
	 * @author jrschmie Jacob Schmiedl
	 *
	 * @param <E> generic data
	 */
    private static class PositionalNode<E> implements Position<E> {
    	/** The element that holds the data */
        private E element;
        /** The next node in the PositionalNode */
        private PositionalNode<E> next;
        /** The previous node */
        private PositionalNode<E> previous;

        
        public PositionalNode(E value) {
            this(value, null);
        }

        
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets previous node
         * @param prev what previous should be
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Getter for previous
         * @return previous
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Setter for the next node
         * @param next node
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Getter for the next node
         * @return next
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets element
         * @param element E 
         */
        public void setElement(E element) {
            this.element = element;
        }
    }

    /**
     * PositionIterator Class
     * @author jrschmie Jacob Schmiedl
     *
     */
    private class PositionIterator implements Iterator<Position<E>> {
    	/** Current Position */
        private Position<E> current;
        /** Whether the element can be removed */
        private boolean removeOK;
        /** The past position */
        private Position<E> past;

       
        public PositionIterator(PositionalNode<E> start) {
            current = start;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
            return current != null && current != tail;
        }

        @Override
        public Position<E> next() {
            if(!hasNext()) {
            	throw new NoSuchElementException("empty");
            }
            if(current == null) {
            	throw new NoSuchElementException("e");
            }
            past = current;
            current = after(current);
            removeOK = true;
            return past;
        }

        @Override
        public void remove() {
        	if(!removeOK) { 
	        	throw new IllegalStateException("E");
	        }

            PositionalLinkedList.this.remove(past);
            removeOK = false;
        }
    }
    
    /**
     * ElementIterator class
     * @author jrschmie Jacob Schmiedl
     *
     */
    private class ElementIterator implements Iterator<E> {
    	/** The Iterator object that is being used. */
        private Iterator<Position<E>> it;
        
        public ElementIterator(PositionalNode<E> start) {
            it = new PositionIterator(start);
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
            it.remove();
        }
    }

    /**
     * PositionIterable Class
     * @author jrschmie Jacob Schmiedl
     *
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator(front.getNext());
        }
    }
}