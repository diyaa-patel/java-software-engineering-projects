package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * LinkedList class which has references to previous and next ListNode 
 * basic functions: size, add, remove, set, and get 
 * Uses an iterator class to iterate through the list 
 * @param <E> the type of LinkedList 
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** front */ 
	private ListNode front; 
	/** back of the list */ 
	private ListNode back; 
	/** size of the list */ 
	private int size;
	
	
	/**
	 * The constructor creates a ListNode with null data for both front and back 
	 * fronts next points to back and backs prev points to front 
	 * the size is set to 0. 
	 */
	public LinkedList() {
		size = 0; 
		front = new ListNode(null); 
		back =  new ListNode(null, front, null);
		front.next = back;
	}
	
	
	/**
	 * returns the size of the linked list
	 * @return size the size of the linked list 
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * checks if the size of the list is 0 
	 * @return true if the list is empty 
	 */
	public boolean isEmpty() {
		return size == 0; 
	}
	
	/**
	 * Add method will insert element before element returned by next() by using the list iterator
	 * @throws IllegalArgumentException if the element is a duplicate 
	 * @throws NullPointerException if the element is null 
	 */
	@Override
	public void add(int index, E element) {
		if(element == null) {
			throw new NullPointerException(); 
		} 
		else if(this.contains(element)) {
			throw new IllegalArgumentException(); 
		}
		ListIterator<E> iterator = this.listIterator(index); 
		iterator.add(element);
	}
	
	/**
	 * Set method will set the element at that index 
	 * @return data the prev data in the cell 
	 * @throws IllegalArgumentException if lastRetrieved is null or is a duplicate element 
	 * @throws NullPointerException if the element to add is null 
	 * @throws IndexOutOfBoundsException if list is empty and setting at index 0. 
	 */
	@Override 
	public E set(int index, E element) {
		if(index == 0 && size() == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		//System.out.println(index + "+" + element); 
		if(element == null) {
			throw new NullPointerException(); 
		} 
		if(this.contains(element)) {
			throw new IllegalArgumentException(); 
		} 
		ListIterator<E> iterator = this.listIterator(index); 
		if(iterator.hasNext()) {
			E data = iterator.next();
			iterator.set(element);
			return data;
		} 
		return null; 
	}
	
	

	/**
	 * list iterator that iterates through the list 
	 * @param index where to iterate to 
	 * @throws IndexOutOfBoundsException when index is out of bounds
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		if(index > size || index < -1)
		{
			throw new IndexOutOfBoundsException();
		}
		LinkedListIterator listit = new LinkedListIterator(index);
		return listit;
	}
	
	/**
	 * inner class ListNode which constructs ListNodes for the LinkedList 
	 * has pointers to next and previous ListNode 
	 */
	private class ListNode {
		/** data in the node */ 
		public E data; 
		/** next node in list */ 
		public ListNode next; 
		/** previous node in the list */ 
		public ListNode prev; 
		
		/**
		 * Constructor for ListNode with only data parameter rest is null and call to other constructor 
		 * @param data the element in the ListNode 
		 */
		public ListNode(E data) {
			this(data, null, null); 
		}
		
		/**
		 * Constructs a ListNode with data, a previous pointer, and a next pointer 
		 * @param data the element in the ListNode
		 * @param prev the pointer to previous ListNode
		 * @param next the pointer     to the next ListNode
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			 this.data = data; 
		     this.next = next; 
		     this.prev = prev; 
		}
	}
	
	/**
	 * Iterates through the LinkedList for add, set, remove, hasNext, hasPrevious methods 
	 * is called when utilizing these methods which are overidden in LinkedList class 
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** previous index */ 
	    public int previousIndex; 
	    /** next index */ 
	    public int nextIndex; 
	    /** previous ListNode */ 
	    public ListNode previous; 
	    /** next ListNode */ 
	    public ListNode next; 
	    /** last retreived ListNode */ 
	    private ListNode lastRetrieved; 
	    
	    /**
	     * accepts an index to position the iterator. 
	     * @param index the index to itereate to 
	     * @throws IndexOutOfBoundsException if index is out of bounds 
	     */
	    public LinkedListIterator(int index) {
	    	if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
            this.previousIndex = index - 1;
	    	this.nextIndex = index - 1;
	    	this.lastRetrieved = null;	
          	next = front;
          	previous = null;
			for(int i = 0; i < index; i++)
			{
			    next = next.next;
				previous = next.prev;
			}
    	}

	    /**
	     * returns true if there is a next node in the list
	     * @return true is there is a next node in the list 
	     */
		@Override
		public boolean hasNext() {
			return next != null && next.next != null && !next.next.equals(back);
		}

		/**
		 * gets the next element in the list 
		 * @return lastRetrieved.data the data in the last retrived list node 
		 * @throws NoSuchElementException if there is no next ListNode 
		 */
		@Override
		public E next() {
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
            next = next.next;
            previous = next.prev;
            lastRetrieved = next;
            nextIndex++;
            previousIndex++;
			return lastRetrieved.data;
		}

		/**
		 * returns true if there is a previous node in the list
		 * @return true if there is a previous node 
		 */
		@Override
		public boolean hasPrevious() {
			return previous != null && previous.prev != null && !previous.equals(front);
		}

		/**
		 * gets the previous node in the list 
		 * @return lastRetrieved.data the data of the last retrieved node 
		 * @throws NoSuchElementException if there is not a previous node 
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            
            lastRetrieved = previous;
            next = previous.next;
            previous = previous.prev;
            nextIndex--;
            previousIndex--;
			return lastRetrieved.data;
		}

		/**
		 * returns the next node's index in the list
		 * @return nextIndex the next node's index 
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * returns the previous node's index in the list
		 * @return previousIndex the previous node's index 
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * removes the element returned by the last call to previous() or next()
		 * @throws IllegalArgumentException if lastRetrieved is null 
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalArgumentException(); 
			}
			//next = next.next;
			previous.next = next.next; 
			//next.next = lastRetrieved.next; 
			size--; 
		}

		/**
		 * Replaces the last element returned by next() or previous() with the specified element (optional operation). This call can be made 
         * only if neither remove() nor add(E) have been called after the last call to next or previous.
         * @throws IllegalArgumentException if lastRetrieved is null 
		 */
		@Override
		public void set(E element) {
			
			if(lastRetrieved == null) {
				throw new IllegalArgumentException(); 
			}
			next.data = element;  
		}
		

		/**
		 * Add method will insert element before element returned by next()
		 */
		@Override
		public void add(E element) {
			if(front.next.equals(back)) {
				previous = front;
			}
			else {
				previous = next;
			}
			next = new ListNode(element, previous, next.next);
			previous.next = next;
			size++;
			lastRetrieved = null;
		}
		
	}

}
