/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

/**
 * Abstract Class that implements List
 * @author jrschmie Jacob Schmiedl
 * @param <E> data that is from the list.
 */
public abstract class AbstractList<E> implements List<E> {

	@Override
	public void addFirst(E value) {
		add(0, value);
	}
	
	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Method that checks if the index is in bounds.
	 * @param index int that represents the index of the list.
	 */
	protected void checkIndex(int index)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	/**
	 * Method that checks if the index is in bounds when adding.
	 * @param index int that represents the index of the list.
	 */
	protected void checkIndexForAdd(int index)
	{
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	@Override
	public E first() {
		return get(0);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}
	
	@Override
	public E removeFirst() {
		return remove(0);
	}
	
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}

}
