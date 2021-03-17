package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.Position;

/**
 * BinarySearchTreeMapTest class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class BinarySearchTreeMapTest {
	/** Tree */
	BinarySearchTreeMap<Integer, String> tree;
	
	/**
	 * Setup method
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}
	
	/**
	 * Put method
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
		
		tree.put(2, "two");
		tree.put(3, "three");
		tree.put(4, "four");
		tree.entrySet();
		tree.toString();
		Iterable<Position<Entry<Integer, String>>> it = tree.children(tree.root());
		assertTrue(it.iterator().hasNext());
		assertTrue(tree.isInternal(tree.root()));
		assertEquals(2, tree.numChildren(tree.root()));
		
		Iterable<Position<Entry<Integer, String>>> it1 = tree.preOrder();
		assertEquals("one", it1.iterator().next().getElement().getValue());
		
		Iterable<Position<Entry<Integer, String>>> it2 = tree.postOrder();
		assertTrue(it2.iterator().hasNext());
		
		Iterable<Position<Entry<Integer, String>>> it3 = tree.levelOrder();
		assertEquals("one", it3.iterator().next().getElement().getValue());
		
		Iterable<Position<Entry<Integer, String>>> it4 = tree.inOrder();
		assertEquals(null, it4.iterator().next().getElement());
		
		Position<Entry<Integer, String>> p = tree.root();
		Entry<Integer, String> entry = tree.preOrder().iterator().next().getElement();
		tree.set(p, entry);
	}
	
	/**
	 * Get Method
	 */
	@Test
	public void testGet() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		assertEquals("one", tree.get(1));
	}
	
	/**
	 * Remove method
	 */
	@Test
	public void testRemove() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		assertNull(tree.remove(10));
		assertEquals(1, tree.size());
		
		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
		
	}
}
