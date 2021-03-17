package edu.ncsu.csc316.dsa.queue;


import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.java
 * @author jrschmie Jacob Schmiedl
 *
 */
public class ArrayBasedQueueTest {

	/** Queue that is used in the test class */
    private Queue<String> queue;
    
    /**
     * Setup method
     */
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }
    
    /**
     * Tests the enqueue method
     */
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
    }
    
    /**
     * Tests the dequeue method
     */
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals(5, queue.size());
        
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    /**
     * Tests the front method
     */
    @Test
    public void testFront() {
    	assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        assertEquals(4, queue.size());
        assertEquals("one", queue.front());
    }

}