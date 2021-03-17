package edu.ncsu.csc316.dsa.stack;

/**
 * Stack.java
 * Stack interface
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public interface Stack<E> {
	/**
	 * Adds the value at the top of the stack
	 * @param value E generic data that is being added to the stack
	 */
    void push(E value);
    
    /**
     * Removes the E value from the top of the stack
     * @return value E generic data that is at the top of the stack
     */
    E pop();
    
    /**
     * Returns the top of the stack
     * @return value E generic data that is at the top of the stack
     */
    E top();
    
    /**
     * Returns the size of the stack
     * @return int of size of the stack
     */
    int size();
    
    /**
     * Returns true or false on whether or not the stack is empty.
     * @return true of false whether the stack is empty or not.
     */
    boolean isEmpty();
}

