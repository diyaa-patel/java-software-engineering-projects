package edu.ncsu.csc216.pack_scheduler.util;


import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

/**
 * Test class for ArrayQueue
 * @author Ramcharan
 */
public class ArrayQueueTest {
    /** Queue for testing */
    private Queue<String> queue;
    /** Constant for testing capacity */
    private static final int CAPACITY = 5;
    
    /**
     * Set up method run before each test
     */
    @Before
    public void setUp() {
        queue = new ArrayQueue<String>(CAPACITY);
    }
    
    /**
     * Tests queue construction
     */
    @Test
    public void testConstruction() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Test invalid construction
        assertThrows(IllegalArgumentException.class, () -> new ArrayQueue<String>(-1));
    }
    
    /**
     * Tests enqueuing a single element
     */
    @Test
    public void testEnqueueSingle() {
        queue.enqueue("Element");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    /**
     * Tests enqueuing multiple elements
     */
    @Test
    public void testEnqueueMultiple() {
        queue.enqueue("First");
        assertEquals(1, queue.size());
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        queue.enqueue("Third");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    /**
     * Tests dequeuing a single element
     */
    @Test
    public void testDequeueSingle() {
        queue.enqueue("Element");
        assertEquals("Element", queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
    
    /**
     * Tests dequeuing multiple elements
     */
    @Test
    public void testDequeueMultiple() {
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        assertEquals(3, queue.size());
        assertEquals("First", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("Second", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("Third", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Tests enqueuing when queue is full
     */
    @Test
    public void testEnqueueWhenFull() {
        // Fill the queue
        for (int i = 0; i < CAPACITY; i++) {
            queue.enqueue("Element" + i);
        }
        assertEquals(CAPACITY, queue.size());
        
        // Try to add one more element
        assertThrows(IllegalArgumentException.class, 
            () -> queue.enqueue("Overflow"));
    }
    
    /**
     * Tests dequeuing from empty queue
     */
    @Test
    public void testDequeueEmpty() {
        assertThrows(NoSuchElementException.class, 
            () -> queue.dequeue());
    }
    
    /**
     * Tests enqueuing null element
     */
    @Test
    public void testEnqueueNull() {
        assertThrows(IllegalArgumentException.class, 
            () -> queue.enqueue(null));
    }
    
    /**
     * Tests setting invalid capacity
     */
    @Test
    public void testSetNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, 
            () -> queue.setCapacity(-1));
    }
    
    /**
     * Tests setting capacity less than size
     */
    @Test
    public void testSetCapacityLessThanSize() {
        queue.enqueue("First");
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        
        assertThrows(IllegalArgumentException.class, 
            () -> queue.setCapacity(1));
    }
    
    /**
     * Tests interleaved enqueue and dequeue operations
     */
    @Test
    public void testInterleavedOperations() {
        queue.enqueue("First");
        assertEquals(1, queue.size());
        queue.enqueue("Second");
        assertEquals(2, queue.size());
        assertEquals("First", queue.dequeue());
        assertEquals(1, queue.size());
        queue.enqueue("Third");
        assertEquals(2, queue.size());
        assertEquals("Second", queue.dequeue());
        assertEquals("Third", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Tests setting valid capacity
     */
    @Test
    public void testSetValidCapacity() {
        queue.setCapacity(10);
        for (int i = 0; i < 7; i++) {
            queue.enqueue("Element" + i);
        }
        assertEquals(7, queue.size());
        
        // Test setting capacity equal to size
        queue.setCapacity(7);
        assertEquals(7, queue.size());
    }
    
    /**
     * Tests queue behavior at capacity
     */
    @Test
    public void testQueueAtCapacity() {
        // Fill queue to capacity
        for (int i = 0; i < CAPACITY; i++) {
            queue.enqueue("Element" + i);
        }
        assertEquals(CAPACITY, queue.size());
        
        // Remove one and add one
        assertEquals("Element0", queue.dequeue());
        queue.enqueue("NewElement");
        assertEquals(CAPACITY, queue.size());
    }
}