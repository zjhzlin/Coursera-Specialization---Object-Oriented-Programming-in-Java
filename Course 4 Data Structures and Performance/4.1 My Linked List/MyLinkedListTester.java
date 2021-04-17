/**
 * 
 */
package textgen;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


/**
 * @author UC San Diego MOOC team
 * Lynn ZHANG 2021-4-17
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
		
		// TODO: Add more tests here
		try {
			emptyList.remove(0);
			fail("Empty List cannot remove any more element");
		}
		catch(IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.remove(9);
			fail("shortlist remove index out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		longerList.remove(9);
		assertEquals("Remove: check size is correct", LONG_LIST_LENGTH-1, longerList.size());
		
		String b = shortList.remove(1);
		assertEquals("Remove: check removed string is correct", "B", b);
		
		// to lower index bound
		try {
			longerList.remove(-1);
			fail("cannot remove element where index out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
		
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			shortList.add(null);
			fail("Check null value");
		}
		catch (NullPointerException e) {
			
		}
		
		assertEquals("Check boolean value", true, shortList.add("C"));
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		
		assertEquals("check size is correct ", 3, list1.size());
		assertEquals("check size is correct ", 0, emptyList.size());
		assertEquals("check size is correct ", 2, shortList.size());
		assertEquals("check size is correct ", LONG_LIST_LENGTH, longerList.size());

	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		// test null
		try {
			shortList.add(1, null);
			fail("Check null value");
		}
		catch (NullPointerException e) {
			
		}
		
		// test out of bounds
		try {
			longerList.add(-1, 2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
				
		try {
			longerList.add(LONG_LIST_LENGTH+3, 2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// test content 
		list1.add(1, 666);
		assertEquals("add at index 1, content is 666", (Integer)666, list1.get(1));
		
		list1.add(0, 888);
		assertEquals("add at index 0, content is 888", (Integer)888, list1.get(0));
		
		// test add content to emptyList fail. why???   - in the add method, didn't add size!=0 in the exception
		emptyList.add(0, 999);
		assertEquals("add at index 0 in empty list: 999", (Integer)999, emptyList.get(0));
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			emptyList.set(0,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first old", "A", shortList.set(0, "C"));
		assertEquals("Check second old", "B", shortList.set(1, "D"));
		assertEquals("Check new first", "C", shortList.get(0));
		assertEquals("Check new seond", "D", shortList.get(1));
		
		try {
			shortList.set(-1, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.set(2, "Q");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		// set the longer list to be 100-i value
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " old element", (Integer)i, longerList.set(i, 100-i));
		}
		
		// check the new value correctness
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check new "+i+ " element", (Integer)(100-i), longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.set(-1, 999);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.set(LONG_LIST_LENGTH,999);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
