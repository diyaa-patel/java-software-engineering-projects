package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedListRecursive class which has references to previous and next ListNode 
 * basic functions: size, add, remove, set, and get 
 * Uses an iterator class to iterate through the list, and recursively implements the methods 
 * @param <E> the type of LinkedListRecursive
 */
public class LinkedListRecursive<E> {

	/** front */ 
	private ListNode front; 
	/** size of the list */ 
	private int size;
	
	
	/**
	 * The constructor creates a ListNode with null data for both front and back 
	 * fronts next points to back and backs prev points to front 
	 * the size is set to 0. 
	 */
	public LinkedListRecursive() {
		size = 0; 
		front = null; 
	}
	
	
	/**
	 * returns the size of the linked list
	 * @return size the size of the linked list 
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * add method will insert element into the list 
	 * @param element the element to add 
	 * @return true if element is added to the list 
	 * @throws NullPointerException if element is null 
	 * @throws IllegalArgumentException if there is a duplicate element 
	 */
	public boolean add(E element) {
		if(element == null) {
			throw new NullPointerException(); 
		}
		else if(this.contains(element)) {
			throw new IllegalArgumentException("element already exists."); 
		}
		else if(front == null) {
			front = new ListNode(element); 
			size++; 
			return true; 
		}
		front.add(element); 
		return true;
	}
	
	/**
	 * add method will insert element into the list at the given index 
	 * @param idx the index to add the element to 
	 * @param element the element to add 
	 * @return true if element is added to the list 
	 * @throws NullPointerException if element is null 
	 * @throws IllegalArgumentException if there is a duplicate element 
	 * @throws IndexOutOfBoundsException if index is out of bounds 
	 */
	public boolean add(int idx, E element) {
	    if (element == null) {
	        throw new NullPointerException();
	    } else if (this.contains(element)) {
	        throw new IllegalArgumentException("Element already exists.");
	    } else if (idx < 0 || idx > size) {
	        throw new IndexOutOfBoundsException();
	    } else if (front == null && idx != 0) {
	        throw new IndexOutOfBoundsException();
	    }

	    if (idx == 0) {
	        ListNode temp = front;
	        front = new ListNode(element);
	        front.next = temp;
	        size++;
	        return true;
	    }

	    front.add(idx - 1, element);
	    return true;
	}
	
	/**
	 * Set method will set the element at that index 
	 * @param index the index to set at 
	 * @param element the element to set to the index given 
	 * @return data the prev data in the cell 
	 * @throws IllegalArgumentException if lastRetrieved is null or is a duplicate element 
	 * @throws NullPointerException if the element to add is null 
	 * @throws IndexOutOfBoundsException if list is empty and setting at index 0. 
	 */
	public E set(int index, E element) {
		if (element == null) {
	        throw new NullPointerException();
	    } 
		else if(this.contains(element)) {
			throw new IllegalArgumentException(); 
		}
		else if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    return front.set(index, element);
	}
	
	/**
	 * contains method which returns true if the list contains a duplicate 
	 * @param element the element to check if it is already in list 
	 * @return true if there is a duplicate 
	 */
	public boolean contains(E element) {
		if(size() == 0) {
			return false; 
		}
		else if(front.data == element) {
			return true;
		}
		return front.contains(element);
	}
	
	/**
	 * gets the data at the given index 
	 * @param idx the index of the element to get 
	 * @return element the element at the index 
	 * @throws IndexOutOfBoundsException if index is less than zero or greater than or equal to size 
	 * @throws IllegalArgumentException if the list is empty 
	 */
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException(); 
		}
		else if(size == 0) {
			throw new IllegalArgumentException(); 
		}
		return front.get(idx); 
	}
	
	/**
	 * removes the ListNode at the index given 
	 * @param idx the index to remove in the list 
	 * @return data the data of the element removed 
	 * @throws IndexOutOfBoundsException if the idx is less than 0 or greater than or equal to size 
	 * @throws IllegalArgumentException if the list is empty  
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException(); 
		}
		else if(size == 0) {
			throw new IllegalArgumentException(); 
		} 
		else if (idx == 0) {
	        E removedData = front.data;
	        front = front.next;
	        size--;
	        return removedData;
	    }
	    return front.remove(idx - 1);
	}


	/**
	 * removes the element in the list 
	 * @param element the element to remove 
	 * @return true if the element is removed 
	 */
	public boolean remove(E element) {
		if(element == null || front == null) {
			return false; 
		}
		else if(front.data.equals(element)) {
			front = front.next; 
			size--; 
			return true;  
		}
		return front.remove(element); 
		
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
		 * @param next the pointer to the next ListNode
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			 this.data = data; 
		     this.next = next; 
		}
		
		/**
		 * returns true to see if the list contains the element 
		 * @param element the element to check if it is already in list 
		 * @return true if the list contains the element 
		 */
		public boolean contains(E element) {
			if(next == null) {
				return false; 
			}
			else if(next.data == element) {
				return true; 
			} 
			return next.contains(element); 
		}
		
		/**
		 * calls the element to see if it is null and adds to list else it goes back to the 
		 * public method to get called for the next element 
		 * @param element the element to add to the list 
		 */
		public void add(E element) {
			if(next == null) {
				next = new ListNode(element);
				size++;
			}
			else {
				next.add(element);
			}
		}
		
		/**
		 * calls the element to see if it is null and adds to list else it goes back to the 
		 * public method to get called for the next element 
		 * @param idx the index to add the element at 
		 * @param element the element to add to the list 
		 */
		public void add(int idx, E element) {
			if(idx == 0) {
				ListNode temp = next; 
				next = new ListNode(element);
				next.next = temp; 
				size++;
			}
			else if(next == null) {
				next = new ListNode(element); 
				size++; 
			}
			else {
				next.add(idx - 1, element);
			}
		}
		
		/**
		 * removes the ListNode at the given index 
		 * @param idx the idx to remove the ListNode 
		 * @return dataTemp the data at the removed index 
		 */
		public E remove(int idx) {
			if(idx == 0) {
				E dataTemp = next.data; 
				next = next.next; 
				size--; 
				return dataTemp; 
			}
			return next.remove(idx - 1); 
		}
		
		
		/**
		 * removes the given element from the list 
		 * @param element the element to remove 
		 * @return true if element is removed 
		 */
		public boolean remove(E element) {
			if(next != null) {
				if (element == next.data) {
					next = next.next; 
					size--; 
		            return true; 
		        } 
		        return next.remove(element);
			} 	
			return false; 
		}
		
		/**
		 * returns the data at the given index 
		 * @param idx the idx to get the element 
		 * @return element the data in the ListNode at the given index 
		 * @throws IndexOutOfBoundsException if next is null and index is out of list size 
		 */
		public E get(int idx) {
			if (idx == 0) {
	            return this.data; 
	        } else if (next == null) {
	            throw new IndexOutOfBoundsException("Index out of bounds"); 
	        }
	        return next.get(idx - 1);
		}
		
		/**
		 * sets the element at the given index 
		 * @param idx the index to set the element at 
		 * @param element the element to set at the given index 
		 * @return data the data of the previous ListNode at the index 
		 * @throws IndexOutOfBoundsException if next is null and index is out of list size 
		 */
		public E set(int idx, E element) {
			if (idx == 0) { 
				E temp = this.data; 
				this.data = element; 
	            return temp; 
	        } else if (next == null) {
	            throw new IndexOutOfBoundsException("Index out of bounds"); 
	        }
	        return next.set(idx - 1, element);
			
		}
	}
	
	/**
	 * checks to see if empty 
	 * @return true if return true if size is 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}
