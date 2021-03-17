package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * AVLTreeMapTest
 * @author jrschmie Jacob Schmiedl
 *
 */
public class AVLTreeMapTest {
	/** BinarySearchTree tree */
    private BinarySearchTreeMap<Integer, String> tree;
    /** BinarySearchTree tree1 */
    private BinarySearchTreeMap<Integer, String> tree1;
    
    /**
     * Setup method
     */
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>(null);
        tree1 = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Put method
     */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        
        assertNull(tree.put(10, "string10"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        assertEquals(2, tree.size());
        
        tree1.put(1, "one");
        tree1.put(2, "two");
        tree1.put(3, "three");
        tree1.put(4, "four");
        tree1.put(5, "five");
        tree1.put(6, "six");
        tree1.put(7, "seven");
        tree1.put(8, "eight");
        tree1.remove(3);
        
    }
    
    /**
     * Get method
     */
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(3, "string3"));
        assertFalse(tree.isEmpty());
        
        assertEquals("string3", tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(0));
        
        assertEquals(null, tree.get(4));
        tree.put(4, "four");
        assertEquals("four", tree.get(4));
    }
    
    /**
     * Remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        
        assertNull(tree.remove(0));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
    }
}
