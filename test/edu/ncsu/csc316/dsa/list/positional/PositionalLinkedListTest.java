package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * PositionalLinkedList test class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class PositionalLinkedListTest {

	/**
	 * The PositionalList of Strings used in the test
	 */
	private PositionalList<String> list;
	
	/**
	 * The setup method for the test class
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}
	
	/**
	 * Tests getting the first in the list
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		assertNull(list.first());
		
		try{
			list.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		
	}
	
	/**
	 * Tests getting the last
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());
	}
	
	/**
	 * Tests adding at the beginning
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(first, list.first());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
	}
	
	/**
	 * Tests adding at the end
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(first, list.last());
		assertEquals(1, list.size());
		
	}
	
	/**
	 * Tests adding before
	 */
	@Test
	public void testBefore() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		Position<String> second = list.addFirst("two");
		Position<String> third = list.addFirst("three");
		assertEquals(third, list.before(second));
	}
	
	/**
	 * Tests adding after
	 */
	@Test
	public void testAfter() {
		Position<String> first = list.addFirst("one");
		assertEquals(first, list.first());
		Position<String> second = list.addFirst("two");
		Position<String> third = list.addFirst("three");
		assertEquals(second, list.after(third));
	}
	
	/**
	 * Tests adding before
	 */
	@Test
	public void testAddBefore() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addBefore(first, "two");
		assertEquals(second, list.before(first));
	}
	
	/**
	 * Tests adding after
	 */
	@Test
	public void testAddAfter() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addAfter(first, "two");
		assertEquals(second, list.after(first));
	}
	
	/**
	 * Tests setting method
	 */
	@Test
	public void testSet() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addAfter(first, "two");
		assertEquals(second, list.after(first));
		list.set(first, "firstElement");
		assertEquals("firstElement", list.first().getElement());
	}
	
	/**
	 * Test the remove
	 */
	@Test
	public void testRemove() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addAfter(first, "two");
		list.remove(second);
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests the Iterator method
	 */
	@Test
	public void testIterator() {
		list.addFirst("first");
		list.addLast("second");
		list.addLast("third");
		Iterator<String> it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("first", it.next());
		it.remove();
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests the Positions method
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		
	}

}
