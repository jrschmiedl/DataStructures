/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SinglyLinkedList that points at the front and tail of the list
 * @author jrschmie Jacob Schmiedl
 * 
 * @param <E> data
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** integer that represents the size of the list */
    private int size;
    /** The front node in the linked list */
    private LinkedListNode<E> front;
    /** The tail node in the linked list */
    private LinkedListNode<E> tail;
    
    /**
     * Constructor for the sentinal front node
     */
    public SinglyLinkedList() {
        // Let front be a dummy (sentinel) node
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0; 	
    }
    
    @Override
    public void add(int index, E value) {
    	checkIndexForAdd(index);
    	LinkedListNode<E> temp = new LinkedListNode<E>(value);
    	LinkedListNode<E> current = front.next;
    	if (index == 0) {
    		if (front.next == null) {
    			tail = temp;
    			current = front.next;
    		}
    		temp.next = front.next;
    		front.next = temp;
    		size++;
    	} else if (index == size) {
    		temp.next = null;
    		current = tail;
    		this.tail = temp;
    		current.next = tail;
    		size++;
    	} else {
    		for (int i = 0; i < index - 1; i++) {
    			current = current.next;
    		}
    		temp.next = current.next;
    		current.next = temp;
    		size++;
    	}
    }
    
    @Override
	public Iterator<E> iterator() {
		return new ElementIterator(front.getNext());
	}
    
    @Override
    public E last() {
    	return tail.getElement();
    }

	@Override
	public E get(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E element = null;
		LinkedListNode<E> current = front.next;
		if (front.next == null) {
			return element;
		}
		for (int i = 0; i <= index && current != null; i++) {
			element = current.data;
			current = current.next;
		}
		return element;
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E element = get(index);
		if (index == 0) {
			front.next = front.next.next;
		} else {
			LinkedListNode<E> current = front.next;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
		size--;
		return element;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E element = this.get(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.data = value;
		return element;
	}

	@Override
	public int size() {
		return size;
	}
        
	/**
     * LinkedListNode class that points to the next node
     * @author jrschmie Jacob Schmiedl
     *
     * @param <E> data
     */
    private static class LinkedListNode<E> {
    	/** Generic data */
        private E data;
        /** The next node in the LinkedList */
        private LinkedListNode<E> next;
        
        public LinkedListNode(E data, LinkedListNode<E> next) {
        	this.data = data;
        	this.next = next;
        }
        
        public LinkedListNode(E data) {
        	this(data, null);
        }
        
        public E getElement() {
        	return data;
        }
        
        public LinkedListNode<E> getNext() {
        	return next;
        }
    }
        
    /**
     * ElementIterator class which can determine if a node can be removed.
     * @author jrschmie Jacob Schmiedl
     *
     */
    private class ElementIterator implements Iterator<E> {
    	/** The current node in the LinkedList */
        private LinkedListNode<E> current;
        /** The previous node in the LinkedList */
        private LinkedListNode<E> previous;
        /** The node before the previous node in the LinkedList */
        private LinkedListNode<E> previousPrevious;
        /** Whether or not the remove is okay */
        private boolean removeOK;

        public ElementIterator(LinkedListNode<E> start) {
            current = start;
            setRemoveOK(false);
        }

        public boolean hasNext() {
            if (current == null) {
            	return false;
            }
            return true;
        }

        public E next() {
        	if (!hasNext()) {
            	throw new NoSuchElementException("e");
            }
            setRemoveOK(true);
            E temp = getCurrent().data;
            previousPrevious = previous;
            setPrevious(current);
            current = current.next;
            return temp;
        }
            
        private LinkedListNode<E> getCurrent() {
			return current;
		}

		private void setPrevious(LinkedListNode<E> current2) {
			previous = current;
		}

	    public void remove() {
            if (!isRemoveOK()) {
            	throw new IllegalStateException("E");
            }
            previousPrevious = getPreviousPrevious();
            previousPrevious = previous;
            previous = null;
            size--;
            setRemoveOK(false);
        }

		private LinkedListNode<E> getPreviousPrevious() {
			return previousPrevious;
		}

		private boolean isRemoveOK() {
			return removeOK;
		}

		private void setRemoveOK(boolean b) {
			this.removeOK = b;
		}
    }
}