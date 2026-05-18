package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Interface for a Queue data structure
 * @param <E> Generic type for elements in the Queue
 */
public interface Queue<E> {
    
    /**
     * Adds an element to the back of the Queue
     * @param element element to add to the Queue
     * @throws IllegalArgumentException if there is no room (capacity has been reached)
     */
    void enqueue(E element);
    
    /**
     * Removes and returns the element at the front of the Queue
     * @return element at the front of the Queue
     * @throws NoSuchElementException if the Queue is empty
     */
    E dequeue();
    
    /**
     * Checks if the Queue is empty
     * @return true if the Queue is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the number of elements in the Queue
     * @return number of elements in the Queue
     */
    int size();
    
    /**
     * Sets the Queue's capacity
     * @param capacity desired capacity of the Queue
     * @throws IllegalArgumentException if capacity is negative or less than 
     * the number of elements in the Queue
     */
    void setCapacity(int capacity);
}