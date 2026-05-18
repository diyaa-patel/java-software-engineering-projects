package edu.ncsu.csc216.pack_scheduler.util;

/**
 * interface for Stack classes 
 * @param <E> the type of the stack 
 */
public interface Stack<E> {
	
	/** 
	 * push method for stack 
	 * @param element the element to push 
	 */ 
	 void push(E element);
	
	/** 
	 * pop method for stack
	 * @return E the element popped 
	 */
	 E pop(); 
	
	/**
	 * checks if stack is empty 
	 * @return false if the stack is not empty 
	 */
	 boolean isEmpty(); 
	
	/**
	 * gets the size of the stack 
	 * @return size the size of the stack 
	 */
	 int size(); 
	
	/**
	 * sets the capacity of the stack 
	 * @param capacity the capacity to set the stack to 
	 */
	 void setCapacity(int capacity); 
	
	

}