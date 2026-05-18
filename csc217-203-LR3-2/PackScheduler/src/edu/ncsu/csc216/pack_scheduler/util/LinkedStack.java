package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The LinkedStack Class creates a stack using the LinkedAbstractList and has functions of a stack 
 * such as pop, push, size, etc. 
 * @author Diya Patel 
 * @author Preeti Joshi 
 * @author Ram 
 * @param <E> the type of LinkedStack 
 */
public class LinkedStack<E> implements Stack<E> {

	/**top node in the stack **/
	private ListNode top; 
	/**capacity**/
	private int capacity;
	/** size of the stack */
	private int size; 
	
	/**
	 * Constructs a LinkedStack and makes the top of the stack at index 0 
	 * @param capacity the capacity of the stack 
	 */
	public LinkedStack(int capacity) {
		setCapacity(capacity); 
		this.top = null;
		this.size = 0; 
	}
	
	/**
	 * pushes the element into the stack 
	 * @param element the element to push
	 * @throws IllegalArgumentException if the stack is at capacity 
	 * @throws NullPointerException if element is null  
	 */
	public void push(E element) {
		if(element == null) {
			throw new NullPointerException(); 
		}
		if (size() >= capacity) {  
			throw new IllegalArgumentException(); 
		}
		
		ListNode newNode = new ListNode(element); 
		newNode.next = top; 
		top = newNode; 
		size++; 
	}


	/**
	 * pops the element that is on top of the stack 
	 * @return element the element that was popped 
	 * @throws EmptyStackException if list is empty 
	 */
	public E pop() {
		if(isEmpty()) 
		{
			throw new EmptyStackException();
		}
		E element = top.data; 
		top = top.next; 
		size--; 
		return element; 
	}
	

	
	/**
	 * checks to see if stack is empty 
	 * @return true if the stack is empty 
	 */
	public boolean isEmpty() {
		return this.top == null; 
	}

	
	/**
	 * gets the size of the stack 
	 * @return size the size of the stack 
	 */
	public int size() {
		return this.size;
	}


	/**
	 * sets the capacity of the stack 
	 * @param capacity the capacity of the stack 
	 * @throws IllegalArgumentException if the capacity is less than zero 
	 * or less than current size 
	 */
	public void setCapacity(int capacity) {
		if(capacity < size() || capacity < 0) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}	
		
	}
	
	/**
	 * inner class of LinkedStack which has 
	 * two private fields and two constructors 
	 */
	private class ListNode {
		/** the data in the node */ 
		private E data; 
		/** the next node in the list */ 
		private ListNode next; 
		
		/**
		 * constructor with only one param and calls other constructor  
		 * @param data the data in the node 
		 */
		public ListNode(E data) {
			this(data, null);  
		}
		
		/**
		 * constructor with only two param 
		 * @param data the data in the node 
		 * @param next the next node in the list 
		 */
		public ListNode(E data, ListNode next) {
			  
			 System.out.println(size + "-" + data);
		     this.data = data; 
		     this.next = next; 
		}	
	}

}
