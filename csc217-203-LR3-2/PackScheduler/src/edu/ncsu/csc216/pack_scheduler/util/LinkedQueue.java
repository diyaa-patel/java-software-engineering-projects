
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Linked list-based implementation of the Queue interface.
 * @param <E> The type of elements held in the queue
 * @author rrkottam
 */
public class LinkedQueue<E> implements Queue<E> {
    /** List to store queue elements */
    private LinkedAbstractList<E> list;
    
    /**
     * Constructs an empty LinkedQueue with the given capacity.
     * @param capacity the maximum number of elements the queue can hold
     * @throws IllegalArgumentException if capacity is negative
     */
    public LinkedQueue(int capacity) {
        list = new LinkedAbstractList<E>(capacity);
    }
    
    /**
     * adds element to back of queue 
     * @throws IllegalArgumentException if element is null 
     */
    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null element");
        }
        list.add(list.size(), element);
    }
    
    /**
     * removes the first element in queue 
     * @return list.remove(0) the element at the first index 
     * @throws NoSuchElementException if queue is empty 
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.remove(0);
    }
    
    /**
     * checks if list is empty 
     * @return true if list is empty 
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * gets the size of the list 
     * @return list.size() the size of the list 
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * sets the capacity of the list 
     * @throws IllegalArgumentException if the capacity is less than zero 
     */
    @Override
    public void setCapacity(int capacity) {
        list.setCapacity(capacity);
    }
}
