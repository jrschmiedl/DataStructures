package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * UnorderedArrayMapTest class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class UnorderedArrayMapTest {
	/** Map */
	private Map<Integer, String> map;
	
	/**
	 * Setup method
	 */
	@Before
	public void setUp() {
		map = new UnorderedArrayMap<Integer, String>();
	}
	
	/**
	 * Test method for put
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedArrayMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedArrayMap[5, 3]", map.toString());
		assertEquals(2, map.size());
		
		assertEquals(map.put(3, "string3"), "string3");
	}
	
	/**
	 * Test method for get
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
	}
	
	/**
	 * Test method for remove
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedArrayMap[1, 4, 2, 5, 3]", map.toString());
	}
	
	/**
	 * Test method for Iterator
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
	}
	
	/**
	 * Test method for EntrySet
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int)(entry.getKey()));
		assertEquals("string1", (String)(entry.getValue()));
	}
	
	/**
	 * Test method for Values
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
	}
}