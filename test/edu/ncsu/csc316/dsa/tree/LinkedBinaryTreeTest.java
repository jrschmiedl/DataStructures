package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * LinkedBinaryTreeTest
 * @author jrschmie Jacob Schmiedl
 *
 */
public class LinkedBinaryTreeTest {

	/** Tree */
    private LinkedBinaryTree<String> tree;

	/** Tree 2 */
    private LinkedBinaryTree<String> tree2;

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
    
//    /**
//     * Helper class to create an invalid position to help test validate(p)
//     */
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
        tree = new LinkedBinaryTree<String>();
        tree2 = new LinkedBinaryTree<String>();
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Set method
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "hi"));
    }
    
    /**
     * Size method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Number of children method
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
     * Iterator methods
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> previous = tree.iterator();
        assertEquals("six", previous.next());
    }
    
    /**
     * Sibling method
     */
    @Test
    public void testSibling() {
        createTree();
        assertEquals(two.getElement(), tree.sibling(three).getElement());
    }
    
    /**
     * IsInternal method
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
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(five, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
    }
    
    /**
     * PostOrder method
     */
    @Test
    public void testPostOrder() {
    	createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        assertEquals(six, post.next());
        assertEquals(seven, post.next());
        assertEquals(five, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
    }
    
    /**
     * InOrder method
     */
    @Test
    public void testInOrder() {
    	createTree();
        Iterator<Position<String>> post = tree.inOrder().iterator();
        assertEquals(six, post.next());
    }
    
    /**
     * EmptyTree method
     */
    @Test
    public void testEmptyTree() {
        assertTrue(tree2.isEmpty());
    }
    
    /**
     * Level order method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(five, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
    }
    
    /**
     * Add children method
     */
    @Test
    public void testAddChildren() {
        tree2.addRoot("root");
        tree2.addLeft(tree2.root(), "root2");
        assertEquals(2, tree2.size());
    }
    
    /**
     * Remove method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals("nine", tree.remove(nine));
        
        tree.remove(eight);
        tree.remove(seven);
        tree.remove(six);
        tree.remove(five);
        tree.remove(four);
        tree.remove(three);
        
        tree.remove(one);
    }
}