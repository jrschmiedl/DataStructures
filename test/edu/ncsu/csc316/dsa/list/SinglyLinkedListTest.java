/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * SinglyLinkedList Test class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class SinglyLinkedListTest {

	/**
	 * List of Strings used in the tests
	 */
	private List<String> list;
	
	/**
	 * The Setup method for the class
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
	}
	
	/**
	 * Tests the set method
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
	
	/**
	 * Tests the first method
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
	 * Tests the add First method
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
	 * Test the Remove first method
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
	 * Test the getting the last method
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
	 * Tests the add last element
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
	 * Tests the remove last element of the list
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
	 * Tests the add index method
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
	 * Tests the remove at index method
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
	 * Test the iterator method
	 */
	@Test
	public void testIterator() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		Iterator<String> it = list.iterator();
		
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		list.addLast("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		list.addLast("two");
		list.addLast("three");
		list.addLast("four");
		list.addLast("five");
		
		Iterator<String> iterator = list.iterator();
		assertEquals("one", iterator.next());
		iterator.next();
		iterator.remove();
		assertEquals(4, list.size());
		
	}
}
