package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size = 0;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null) {
			throw new NullPointerException("Null elements cannot be added.");
		}
		LLNode<E> node = new LLNode<E>(element);
		if(head == null) {
			head = node;
			tail = node;
			size = 1;
			return true;
		}
		else {
			tail.next = node;
			node.prev = tail;
			tail = node;
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException("The position index is not valid.");
		}
		LLNode<E> curr = head;
		for(int i = 0; i < size; i++) {
			if(i == index){
				return curr.data;
			}
			curr = curr.next;
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null)
			throw new NullPointerException("Null elements cannot be added.");
		if((index > size) || (index < 0)) {
			throw new IndexOutOfBoundsException("The position index is not valid.");
		}
		
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> curr = head;
		for(int i = 0; i <= size; i++) {
			if(index == 0) {
				if(head != null) {
					node.next = head;
					head.prev = node;
					head = node;
				}
				else {
					head = node;
					tail = node;
				}
				
				size++;
				return;
			}
			else if(index == size && i == size - 1) {
				node.next = null;
				node.prev = curr;
				curr.next = node;
				tail = node;
				size++;
				return;
			}
			if(i == index-1){
				node.next = curr.next;
				curr.next = node;
				node.prev = curr;
				node.next.prev = node;
				size++;
				return;
			}
			curr = curr.next;
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
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
		if((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException("The position index is not valida.");
		}
		
		LLNode<E> curr = head;
		for(int i = 0; i < size; i++) {
			if(i == index) {
				if(index == 0) {
					if(curr.next != null) {
						head = curr.next;
					}
					else {
						head = null;
						tail = null;
					}
				}
				else if(index == size - 1) {
					curr.prev.next = null;
					tail = curr.prev;
					
				}
				else {
					curr.next.prev = curr.prev;
					curr.prev.next = curr.next;
				}
				size--;
				return curr.data;
			}
			curr = curr.next;
		}
		return null;
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
		if((index >= size) || (index < 0)) 
			throw new IndexOutOfBoundsException("The position index is not valid.");
		
		if(element == null)
			throw new NullPointerException("Null elements cannot be added.");
		
		LLNode<E> curr = head;
		for(int i = 0; i < size; i++) {
			if(i == index) {
				E e = curr.data;
				curr.data = element;
				return e;
			}
			curr =curr.next;
		}
		
		return null;
	}
	
	public String toString() {
		String out = "";
		LLNode<E> curr = head;
		out += "[";
		for(int i = 0; i < size-1; i++) {
			out += curr;
			out += " ,";
			curr = curr.next;
		}
		out += curr;
		out += "]";
		return out;
	}
	
	public static void main (String args[]) {
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		lst.add(0, 1);
		lst.remove(0);
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
	
	public String toString() {
		String str = data.toString();
		return str;
	}

}
