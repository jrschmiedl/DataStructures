package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * SplayTreeMapTest class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class SplayTreeMapTest {
	/** Tree */
    private BinarySearchTreeMap<Integer, String> tree;
    /** Tree 1 */
    private BinarySearchTreeMap<Integer, String> tree1;
    
    /**
     * Setup method
     */
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
        tree1 = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(3, "string3"));
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(5, "five");
        tree.put(6, "six");
        assertEquals(6, tree.size());
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
    }
    
    /**
     * Get method
     */
    @Test
    public void testGet() {
    	tree1.put(1, "one");
        tree1.put(2, "two");
        tree1.put(3, "three");
        tree1.put(4, "four");
        tree1.put(5, "five");
        tree1.put(6, "six");
        assertEquals(6, tree1.size());
        assertEquals("one", tree1.get(1));
        assertEquals("two", tree1.get(2));
    }
    
    /**
     * Remove method
     */
    @Test
    public void testRemove() {
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(5, "five");
        tree.put(6, "six");
        assertEquals(6, tree.size());
        assertEquals("one", tree.remove(1));
    }
}