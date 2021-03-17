package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * RedBlackTreeMapTest
 * @author jrschmie Jacob Schmiedl
 *
 */
public class RedBlackTreeMapTest {
	/** Tree */
    private BinarySearchTreeMap<Integer, String> tree;
    /** Tree 1 */
    private BinarySearchTreeMap<Integer, String> tree1;
    
    /**
     * Setup method
     */
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
        tree1 = new RedBlackTreeMap<Integer, String>(null);
    }
    
    /**
     * Put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertNull(tree.put(7,  "seven"));
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        tree1.put(1, "one");
        tree1.put(2, "two");
        tree1.put(3, "three");
        tree1.put(4, "four");
        tree1.put(5, "five");
        tree1.put(6, "six");
        tree1.put(7, "seven");
        tree1.put(8, "eight");
        assertEquals("eight", tree1.get(8));
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
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
        tree1.put(7, "seven");
        tree1.put(8, "eight");
        assertEquals("eight", tree1.get(8));
    }
    
    /**
     * Remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree1.put(1, "one");
        tree1.put(2, "two");
        tree1.put(3, "three");
        tree1.put(4, "four");
        tree1.put(5, "five");
        tree1.put(6, "six");
        tree1.put(7, "seven");
        tree1.put(8, "eight");
        assertEquals(8, tree1.size());
        assertEquals("eight", tree1.remove(8));
        tree1.remove(1);
        tree1.remove(2);
        tree1.remove(3);
        tree1.remove(4);
        tree1.remove(5);
        tree1.remove(6);
        assertEquals(1, tree1.size());
    }
}
