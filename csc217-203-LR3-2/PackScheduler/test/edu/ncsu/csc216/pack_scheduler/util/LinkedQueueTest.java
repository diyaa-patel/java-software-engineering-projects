package edu.ncsu.csc216.pack_scheduler.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;


/**
 * Tests the LinkedQueue class
 */
public class LinkedQueueTest {
    
    /**
     * Tests LinkedQueue constructor and initial state
     */
    @Test
    public void testLinkedQueue() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Test initial state with different capacity
        LinkedQueue<String> queue2 = new LinkedQueue<String>(10);
        assertEquals(0, queue2.size());
        assertTrue(queue2.isEmpty());
    }
    
    /**
     * Tests enqueue and dequeue with a single element
     */
    @Test
    public void testSingleElement() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        
        // Test single enqueue
        queue.enqueue("Element1");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        // Test single dequeue
        String removed = queue.dequeue();
        assertEquals("Element1", removed);
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Test enqueue after dequeue
        queue.enqueue("NewElement");
        assertEquals(1, queue.size());
        assertEquals("NewElement", queue.dequeue());
    }

    /**
     * Tests enqueue with multiple elements
     */
    @Test
    public void testMultipleElements() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        
        // Add multiple elements
        queue.enqueue("First");
        assertEquals(1, queue.size());
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        queue.enqueue("Third");
        assertEquals(3, queue.size());
        
        // Verify order of removal
        assertEquals("First", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("Second", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("Third", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Test adding after removing all elements
        queue.enqueue("New First");
        assertEquals(1, queue.size());
        assertEquals("New First", queue.dequeue());
    }

    

    /**
     * Tests multiple dequeue operations
     */
    @Test
    public void testMultipleDequeue() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        
        assertEquals("A", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("B", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("C", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    
    /**
     * Tests enqueue at capacity
     */
    @Test
    public void testEnqueueAtCapacity() {
        LinkedQueue<String> queue = new LinkedQueue<String>(2);
        queue.enqueue("First");
        assertEquals(1, queue.size());
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        
        // Try to add when full
        try {
            queue.enqueue("Third");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(2, queue.size());
        }
    }

    /**
     * Tests setCapacity with various scenarios
     */
    @Test
    public void testSetCapacity() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        
        // Test setting larger capacity
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.setCapacity(10);
        assertEquals(2, queue.size());
        
        // Test setting to current size
        queue.setCapacity(2);
        assertEquals(2, queue.size());
        
        // Test operations after capacity change
        assertEquals("First", queue.dequeue());
        queue.enqueue("New");
        assertEquals("Second", queue.dequeue());
        assertEquals("New", queue.dequeue());
        
        // Test setting capacity on empty queue
        queue.setCapacity(3);
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests setCapacity with invalid values
     */
    @Test
    public void testSetCapacityInvalid() {
        LinkedQueue<String> queue = new LinkedQueue<String>(5);
        queue.enqueue("First");
        
        // Test negative capacity
        try {
            queue.setCapacity(-1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(1, queue.size());
        }
        
        // Test capacity less than size
        queue.enqueue("Second");
        try {
            queue.setCapacity(1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(2, queue.size());
        }
    }

    /**
     * Tests interleaved enqueue and dequeue operations
     */
    @Test
    public void testInterleavedOperations() {
        LinkedQueue<String> queue = new LinkedQueue<String>(3);
        
        queue.enqueue("First");
        assertEquals(1, queue.size());
        
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        
        assertEquals("First", queue.dequeue());
        assertEquals(1, queue.size());
        
        queue.enqueue("Third");
        assertEquals(2, queue.size());
        
        assertEquals("Second", queue.dequeue());
        assertEquals(1, queue.size());
        
        assertEquals("Third", queue.dequeue());
        assertEquals(0, queue.size());
        
        queue.enqueue("New First");
        assertEquals(1, queue.size());
        assertEquals("New First", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Tests edge cases with capacity
     */
    @Test
    public void testCapacityEdgeCases() {
        LinkedQueue<String> queue = new LinkedQueue<String>(1);
        
        queue.enqueue("Only");
        assertEquals(1, queue.size());
        
        try {
            queue.enqueue("TooMany");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(1, queue.size());
        }
        
        assertEquals("Only", queue.dequeue());
        assertTrue(queue.isEmpty());
        
        queue.setCapacity(2);
        queue.enqueue("First");
        queue.enqueue("Second");
        assertEquals(2, queue.size());
    }
}