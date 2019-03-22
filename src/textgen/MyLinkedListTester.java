/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		a = longerList.remove(9);
		assertEquals("Remove: check a is correct ", 9, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)8, longerList.get(8));
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		
		try {
			list1.remove(5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.remove(-5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		Integer num = null;
		
		try {
        	list1.add(num);
        	fail("Check out of null pointer");
		}
		catch (NullPointerException e) {
		
		}
		num = 5;
		emptyList.add(num);
		assertEquals("Check end", (Integer)5, emptyList.get(emptyList.size - 1));
		 
		longerList.add(num);
		assertEquals("Check end", (Integer)5, longerList.get(longerList.size - 1));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size", 0, emptyList.size);
		assertEquals("Check size", 2, shortList.size);
		assertEquals("Check size", 10, longerList.size);
		assertEquals("Check size", 3, list1.size);
	}

	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        int index;
        Integer element = (Integer) 13;
        
        try {
        	index = 1;
        	emptyList.add(index, element);
        	fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
        
        try {
        	index = -1;
        	longerList.add(index, element);
        	fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
        
        index = 3;
        try {
        	element = null;
        	list1.add(index, element);
        	fail("Check out of null pointer");
		}
		catch (NullPointerException e) {
		
		}
        
        element = (Integer) 13;
        longerList.add(index, element);
        for(int i = 0; i < longerList.size; i++) {
        	if(index == i) {
        		assertEquals("Check index", (Integer)13, longerList.get(i));
        	}
        }   
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		int index = -4;
		Integer element = 5;
		try {
			list1.set(index, element);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		index = 10;
		try {
			list1.set(index, element);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		index = 1;
		try {
        	element = null;
        	list1.set(index, element);
        	fail("Check out of null pointer");
		}
		catch (NullPointerException e) {
		
		}
		
		element = 5;
		assertEquals("Check set", (Integer)21, list1.set(index, element));
	    
	}
	
	
}
