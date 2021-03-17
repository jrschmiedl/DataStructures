package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayBasedList class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class ArrayBasedListTest {
	/** The List of strings */
	private List<String> list;

	/**
	 * Sets up the test class
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * tests adding to the index
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		
		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you might
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.
		
		try{
			list.add(15,  "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}

	/**
	 * Tests adding to the end of the list
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		list.addLast("last");
		assertEquals("last", list.get(1));
	}

	/**
	 * Tests getting the last element
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		list.add(1, "two");
		list.addLast("last");
		assertEquals("last", list.get(2));
		assertEquals("last", list.last());
	}

	/**
	 * Tests adding to the beginning of the list
	 */
	@Test
	public void testAddFirst() {
		assertTrue(list.isEmpty());
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		list.addFirst("first");
		assertEquals("first", list.get(0));
		list.add(2, "second");
		list.add(3, "third");
		list.add(4, "fourth");
		list.add(5, "fifth");
		assertEquals("fifth", list.get(5));
		assertEquals("third", list.get(3));
	}

	/**
	 * Gets the first element
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.first());
	}

	/**
	 * Tests the Iterator class
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();
		
		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		list.addLast("two");
		Iterator<String> iterator = list.iterator();
		iterator.next();
		iterator.remove();
	}

	/**
	 * Tests the remove at index class
	 */
	@Test
	public void testRemoveIndex() {
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		list.addLast("last");
		list.addLast("latest");
		assertEquals("last", list.remove(1));
	}

	/**
	 * Removes the first element in the list
	 */
	@Test
	public void testRemoveFirst() {
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		list.addLast("last");
		list.addLast("latest");
		assertEquals("one", list.removeFirst());
	}

	/**
	 * Removes the last element in the list
	 */
	@Test
	public void testRemoveLast() {
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		list.addLast("last");
		list.addLast("latest");
		assertEquals("latest", list.removeLast());
	}

	/**
	 * Tests setting method
	 */
	@Test
	public void testSet() {
		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		list.addLast("last");
		list.addLast("latest");
		assertEquals("last", list.set(1, "second"));
	}
}
