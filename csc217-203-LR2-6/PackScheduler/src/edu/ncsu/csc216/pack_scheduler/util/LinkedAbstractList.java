package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Implementing a linked list
 * @author Preeti Joshi
 * @author Diya Patel
 * @param <E> generic type parameter
 */
public class LinkedAbstractList<E> extends AbstractList<E>  {

	/** the front node of the list */ 
	private ListNode front; 
	/** the size of the list */ 
	private int size; 
	/** the capacity of the list */ 
	private int capacity;
	/**current node**/
	ListNode current; 
	
	/**
	 * Constructor that sets size to 0, front to null, and capacity to the parameter 
	 * @param parameter the value to set to the capacity 
	 * @throws IllegalArgumentException if the parameter is less than zero or 
	 * if the capacity is less than the current size 
	 */
	public LinkedAbstractList(int parameter){
		front = null; 
		size = 0; 
		if(parameter < 0) {
			throw new IllegalArgumentException(); 
		}
		setCapacity(parameter); 
		if(capacity < this.size()) {
			throw new IllegalArgumentException(); 
		}
	}
	/**
	 * returns the size of list 
	 * @return size the size of the list 
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * gets the element at the given index 
	 * @param index the index to get the element 
	 * @return element the element at the index 
	 * @throws IndexOutOfBoundsException if index is out of bounds 
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(); 
		}
		ListNode temp = front;
		for(int i = 0; i < index; i++)
		{
			temp = temp.next;
		}
		return temp.data; 
	}
	
	/**
	 * adds element to index in list 
	 * @param index the index to add the element to 
	 * @param element the element to add to the list 
	 * @throws NullPointerException if element is null 
	 * @throws IndexOutOfBoundsException if index is greater than size() or less than 0 
	 * @throws IllegalArgumentException if duplicate element exists 
	 */
	@Override
	public void add(int index, E element) {
		if (size() == capacity)
		{
			throw new IllegalArgumentException();
		}
		if(element == null) {
			throw new NullPointerException("Element cannot be null"); 
		}
		else if(index < 0 || index > size() || index >= capacity) {
			throw new IndexOutOfBoundsException("Invalid"); 
		}
		for(int i = 0; i < size(); i++) 
		{
			if(element.equals(get(i))) 
			{
				throw new IllegalArgumentException("Invalid"); 
			}
		}
		if (index == 0)	
		{
			if(front == null) {
				front = new ListNode(element);
			}
			else {
				front = new ListNode(element, front);
			}
		}
		else 
		{
			current = front;
			for(int i = 0; i < index - 1; i++)
			{
				current = current.next;
			}
			if(current.next == null) {
				current.next = new ListNode(element);
			}
			else {
				current.next = new ListNode(element, current.next);
			}
		}
		this.size++; 
	}
	
	/**
	 * removes the element at the index parameter 
	 * @param index the index to remove
	 * @throws IndexOutOfBoundsException if index is greater than size() or less than 0 
	 * @return the removed element, of type E
	 */
	@Override
	public E remove(int index)
	{
//		System.out.println(size());
//		System.out.println(capacity);
//		System.out.println(index);
		if(index < 0 || index > size() || size() == 0 || index >= capacity) 
		{
			throw new IndexOutOfBoundsException(); 
		}
			E val = null;
			if (index == 0) 
			{ 
				if(front != null) {
					val = front.data;
					front = front.next;
				}
				else {
					val = null; 
				}
			}  
			else 
			{	
				current = front;
				for (int i = 0; i < index - 1; i++) 
				{
					current = current.next;
				}
				if(current.next != null) {
					val = current.next.data;
					current.next = current.next.next;
				}
				else {
					throw new IndexOutOfBoundsException(); 
					//val = null;
				}
			}
			this.size--;
			
			System.out.println("remove" + size + "-" + index + "-" + val);
		return val;	
	}
	
	/**
	 * removes the element at the index parameter 
	 * @param index the index to remove
	 * @throws IndexOutOfBoundsException if index is greater than size() or less than 0 
	 * @return the removed element, of type E
	 */
	@Override
	public E set(int index, E element)
	{
		E data; 
		if(element == null) {
			throw new NullPointerException(); 
		}
		if(index < 0 || index > size() || size() == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		for(int i = 0; i < size(); i++) {
			if(element.equals(get(i))) {
				throw new IllegalArgumentException(); 
			}
		}
		current = front;
		for(int i = 0; i < index - 1; i++)
		{
			current = current.next;
		}
		
		if(current.next == null) {
			data = current.data;
			current.next = new ListNode(element);
		}
		else if(index == 0) {
			data = current.data;
			front = new ListNode(element, current.next);
		}
		else {
			data = current.next.data; 
			current.next = new ListNode(element, current.next.next);
		}
		
		//temp.data = e
		return data;	 
	}
	
	/**
	 * sets capacity
	 * @param capacity - capacity of list
	 */
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	/**
	 * inner class of LinkedAbstractClass which has 
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
