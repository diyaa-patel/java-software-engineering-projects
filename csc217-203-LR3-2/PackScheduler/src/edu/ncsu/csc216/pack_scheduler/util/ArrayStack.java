package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack which using ArrayList implementation to create a stack that has 
 * function of pop, push, set capacity, and size  
 * @param <E> the type for the ArrayStack 
 * @author Diya Patel
 * @author Preeti Joshi
 * @author Ramcharan Reddy
 */
public class ArrayStack<E> implements Stack<E> {

	/**ArrayList list**/
	//private ArrayList<E> list;
	private E[] list; 
	/**top**/
	private int top; 
	/**capacity**/
	private int capacity; 
	/** size */ 
	int size; 
	//size++ = element?? 
	//size == 0 

	/**
	 * Constructor ArrayStack
	 * @param capacity capacity of the stack
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		setCapacity(capacity); 
		//list = new ArrayList<E>();
		list = (E[]) new Object[capacity]; 
		top = 0; 
		size = 0; 
	}
	
	/**
	 * 
	 * Adds the element in the stack
	 * @param element  element to add at on the top of the stack 
	 * @throws IllegalArgumentException if capacity has been reached
	 * @throws NullPointerException if element is null
	 */
	public void push(E element) {
		if(element == null) {
			throw new NullPointerException(); 
		}
		if (top == capacity) {
			throw new IllegalArgumentException(); 
		}
		//list.add(element);  
		list[size++] = element; 
		top++; 
	}

	/**
	 * Removes the element on the top the stack 
	 * @throws EmptyStackException if the stack is empty
	 * @return the element removed from the stack
	 * @throws EmptyStackException if stack is empty 
	 */
	public E pop() {
		if(isEmpty()) 
		{
			throw new EmptyStackException();
		}
		else 
		{
			top--; 
			E element = list[size - 1]; 
			list[size - 1] = null; 
			size--; 
			return element;
		}
	}

	/**
	 * IsEmpty method returns true if s is empty
	 * @return false if s is not empty
	 */
	public boolean isEmpty() {
		return size() == 0; 
	}

	/**
	 * Size method returns size of s 
	 * @return s.size() - the size of s
	 */
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity
	 * @param capacity - the capacity of s
	 * @throws IllegalArgumentException if capacity is less than size or negative
	 */
	public void setCapacity(int capacity) {
		if(capacity < size() || capacity < 0) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}	
	}
	

}
