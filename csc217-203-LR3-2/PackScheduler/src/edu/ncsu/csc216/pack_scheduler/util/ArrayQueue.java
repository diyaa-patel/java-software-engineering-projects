package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Array based implementation of the Queue interface.
 * @param <E> The type of elements held in the queue
 * @author Ramcharan 
 */
public class ArrayQueue<E> implements Queue<E> {
    /** List to store queue elements */
    private ArrayList<E> list;
    /** Maximum capacity of the queue */
    private int capacity;
    
    /**
     * Constructs an empty ArrayQueue with the given capacity.
     * @param capacity the maximum number of elements the queue can hold
     * @throws IllegalArgumentException if capacity is negative
     */
    public ArrayQueue(int capacity) {
        list = new ArrayList<E>();
        setCapacity(capacity);
    }
    
    /**
     * Enqueue method
     * @param element element of type E
     * @throws IllegalArgumentException if element is null or queue is full
     */
    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot add null element");
        }
        if (size() == capacity) {
            throw new IllegalArgumentException("Queue is full");
        }
        list.add(element);
    }
    
    /**
     * removes the first index of queue 
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
     * returns true if the queue is empty 
     * @return true if queue is empty 
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * returns the size of the queue 
     * @return size the size of the queue 
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * sets the capacity of the queue 
     * @throws IllegalArgumentException if capacity is negative or less than current size
     */
    @Override
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        if (capacity < size()) {
            throw new IllegalArgumentException("Capacity cannot be less than current size");
        }
        this.capacity = capacity;
    }
}
