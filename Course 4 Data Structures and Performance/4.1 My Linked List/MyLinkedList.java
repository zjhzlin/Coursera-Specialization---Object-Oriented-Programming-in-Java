package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * Lynn Zhang
 * 2021-04-17 07:07 - 07:50; 08:30 - 09:30
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
			
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// throw exception if null
		if (element == null) {
			throw new NullPointerException("MyLinkedList object cannot store null pointers ");
		}
		
		// else add to the end of the list
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> preNode = tail.prev;
		preNode.next = newNode;   //attention!		
		newNode.prev = preNode;
		tail.prev = newNode;
		newNode.next = tail;
		size += 1;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		// throw exception if index out of bounds;
		if (size == 0 || index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		
		LLNode<E> currNode = head;
		// to find the currNode at index
		for (int i = index; i >= 0 ; i--) {
			currNode = currNode.next;
		}
		return currNode.data;
		//return nextNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		// exception: element null
		if (element == null) {
			throw new NullPointerException("MyLinkedList object cannot store null pointers ");
		}
		// exception: index out of bound
		if ( size!=0 && index > size-1 || index < 0) {     // didn't add size!=0
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		
		// add in index 
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> currNode = head;
		// to find the currNode at index
		for (int i = index; i >= 0 ; i--) {
			currNode = currNode.next;
		}
		LLNode<E> preNode = currNode.prev;
		preNode.next = newNode;
		newNode.prev = preNode;
		currNode.prev = newNode;
		newNode.next = currNode;
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// exception index
		if ( size == 0 || index > size-1 || index < 0) {     
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		
		LLNode<E> currNode = head;
		// to find the currNode at index
		for (int i = index; i >= 0 ; i--) {
			currNode = currNode.next;
		}
		LLNode<E> removeNode = currNode;
		LLNode<E> preNode = currNode.prev;
		LLNode<E> nextNode = currNode.next;
		preNode.next = nextNode;
		nextNode.prev = preNode;
		currNode.next = null;
		currNode.prev = null;
		
		size -= 1;
		
		return removeNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		// index exception
		if ( size == 0 || index > size-1 || index < 0) {     
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		// element exception
		if (element == null) {
			throw new NullPointerException("MyLinkedList object cannot store null pointers ");
		}
				
		LLNode<E> currNode = head;
		// to find the currNode at index
		for (int i = index; i >= 0 ; i--) {
			currNode = currNode.next;
		}
		E oldData = currNode.data;
		currNode.data = element;
		
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
