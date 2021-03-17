package edu.ncsu.csc316.dsa.stack;

/**
 * AbstactStack.java Class for Stack
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public abstract class AbstractStack<E> implements Stack<E> {
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}