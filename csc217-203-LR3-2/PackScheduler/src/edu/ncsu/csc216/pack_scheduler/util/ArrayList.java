package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Class ArrayList that extends the AbstrastList class to create a arraylist with 
 * the basic functions in different methods such as get,add,set,remove etc. 
 * @param <E> the type being passed into array 
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/** size for list */ 
	final private static int INIT_SIZE = 10; 
	/** list - array */ 
	private E[] list; 
	/** size of array */
	private int size; 
	
	/**
	 * ArrayList constructor that creates an array of object and castes to array 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(){ 
		list = (E[]) new Object[INIT_SIZE]; 
	}

	/**
	 * returns the size of the list 
	 * @return size the list size 
	 */
	public int size() {
		return size;
	}

	/**
	 * gets the element at the given index 
	 * @return list[index] the element at the index in the list 
	 * @throws IndexOutOfBoundsException if index is out of bounds 
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(); 
		}
		return list[index]; 
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
		if(element == null) {
			throw new NullPointerException(); 
		}
		else if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(); 
		}
		for(int i = 0; i < size(); i++) {
			if(element.equals(list[i])) {
				throw new IllegalArgumentException(); 
			}
		}
		if(size() == list.length) {
			growArray(); 
		}
	
		for(int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i]; 
		}
		list[index] = element; 
		size++;      
	}
	
	/**
	 * removes the element at the index parameter 
	 * @param index the index to remove 
	 * @throws IndexOutOfBoundsException if index is greater than size() or less than 0 
	 * @return rtn 
	 */
	@Override 
	public E remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(); 
		}
		E rtn = list[index]; 
		for(int i = index; i < size(); i++) {
			list[i] = list[i + 1]; 
		}
		list[size() - 1] = null; 
		size--; 
		return rtn; 
		
	}
	
	/**
	 * return the original element at the location 
	 * @param index the index of the element 
	 * @param element the element that will replace the original one 
	 * @throws NullPointerException if element is null 
	 * @throws IndexOutOfBoundsException if index is greater than size() or less than 0 
	 * @throws IllegalArgumentException if duplicate element exists  
	 */
	@Override 
	public E set(int index, E element) {
		if(element == null) {
			throw new NullPointerException(); 
		}
		else if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(); 
		}
		for(int i = 0; i < size(); i++) {
			if(element.equals(list[i])) {
				throw new IllegalArgumentException(); 
			}
		} 
		E rtn = list[index];
		list[index] = element; 
		return rtn; 
	}
	
//	public boolean add(E element) {
//		list[size] = element; 
//		//size++; 
//		return true; 
//	}
//	
	/**
	 * grows array size to two times the size 
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int s = size * 2; 
		// Create a new array with a larger size
        Object[] newArray = new Object[s];
		System.arraycopy(list, 0, newArray, 0, list.length);
		list = (E[]) newArray;
	}


}