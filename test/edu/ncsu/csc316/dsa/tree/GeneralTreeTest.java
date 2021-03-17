package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * GeneralTreeTest class
 * @author jrschmie Jacob Schmiedl
 *
 */
public class GeneralTreeTest {
	
	/** Tree */
    private GeneralTree<String> tree;

	/** emptyTree */
    private GeneralTree<String> emptyTree;

	/** One */
    private Position<String> one;
    
	/** Two */
    private Position<String> two;

	/** Three */
    private Position<String> three;

	/** Four */
    private Position<String> four;

	/** Five */
    private Position<String> five;

	/** Six */
    private Position<String> six;

	/** Seven */
    private Position<String> seven;

	/** Eight */
    private Position<String> eight;

	/** Nine */
    private Position<String> nine;

	/** Ten */
    private Position<String> ten;
    
//    // An invalid Position to help test validate(p)
//    private class InvalidPosition<E> implements Position<E> {
//
//        @Override
//        public E getElement() {
//            return null;
//        }
//        
//    }
    
    /**
     * Setup method
     */
    @Before
    public void setUp() {
        tree = new GeneralTree<String>();
        emptyTree = new GeneralTree<String>();
    }
    
    /**
     * Helper method to construct a sample tree
     *
     * One
     * -> Two
     *   -> Six
     *   -> Five
     *   -> Ten
     *     -> Seven
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     *
     * Or, visually:
     *                 one
     *            /            \
     *         two                three
     *      /   |     \             |   
     *   six   five   ten          four
     *                  |         /    \
     *                seven     eight  nine              
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addChild(one, "two");
        three = tree.addChild(one,  "three");
        six = tree.addChild(two, "six");
        five = tree.addChild(two, "five");
        ten = tree.addChild(two,  "ten");
        seven = tree.addChild(ten,  "seven");
        four = tree.addChild(three,  "four");
        eight = tree.addChild(four,  "eight");
        nine = tree.addChild(four,  "nine");
    }
    
    /**
     * Set method
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        
//        try {
//            tree.set(new InvalidPosition<String>(), "invalid");
//            fail();
//        } catch(Exception e) {
//            assertTrue(e instanceof IllegalArgumentException);
//        }
        
        assertEquals(10, tree.size());
    }
    
    /**
     * Size method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
    }
    
    /**
     * Number of Children method
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
    }
    
    /**
     * Parent method
     */
    @Test
    public void testParent() {
        createTree();
        assertEquals(one, tree.parent(two));
    }
    
    /**
     * isInternal method
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(two));
    }
    
    /**
     * isLeaf method
     */
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(seven));
    }
    
    /**
     * isRoot method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(ten));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * isEmpty method
     */
    @Test
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        
        createTree();
        assertFalse(tree.isEmpty());
    }
    
    /**
     * PreOrder method
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals(one, pre.next());
        
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(five, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
    }
    
    /**
     * Iterator method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals("one", pre.next());
        
        assertTrue(pre.hasNext());
        try {
        	pre.remove();
        } catch (Exception e) {
        	//pass
        }
    }
    
    /**
     * PostOrder method
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        
        assertEquals(six, post.next());
        assertEquals(five, post.next());
        assertEquals(seven, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
    }
    
    /**
     * LevelOrder method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(five, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
    }
    
    /**
     * AddChild method
     */
    @Test
    public void testAddChild() {
        assertTrue(tree.isEmpty());
        Position<String> one2 = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(one2));
        assertEquals("GeneralTree[\none\n]", tree.toString());
    }
    
    /**
     * Remove method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertEquals(2, tree.numChildren(four));
        tree.remove(nine);
        assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        
        tree.remove(eight);
        tree.remove(seven);
        tree.remove(six);
        tree.remove(five);
        tree.remove(four);
        tree.remove(three);
        
        tree.remove(one);
    }
    
    /**
     * EmptyTree method
     */
    @Test
    public void testEmptyTree() {
        Tree<String> bTree = new GeneralTree<String>();
        assertTrue(bTree.isEmpty());
    }
    
}
