package sort;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

public class MyHeapSpec {
	
	private MyHeap heap;
	
	@Before
	public void setup() {
		heap= new MyHeap(8);
	}
	
	@Test
	public void whenNoElements_ThenIsEmpty() {
		assertTrue(heap.isEmpty());
	}
	
	@Test
	public void whenOneElement_ThenIsNotEmpty() {
		heap.add(555);
		assertFalse(heap.isEmpty());
	}
	
	@Test
	public void whenRemoveSingleElement_ThenElementReturned() {
		heap.add(444);
		assertEquals(444, heap.remove());
	}

	@Test
	public void whenAddUpUntilFullCapacityAndRemove1By1_ThenElementsSorted() {
		int[] unSorted= new int[] {39, 49, 83, 10, 22, 87, 14, 97}; //(has 8 elements)
		for(int e : unSorted) {
			heap.add(e);
		}
		int prev= Integer.MAX_VALUE;
		while(!heap.isEmpty()) {
			int next= heap.remove();
			assertTrue(prev >= next);
			prev=next;
		}
	}
	
	@Test
	/*      50                    15         10
	 *     /  \   goes to -->     /  -->         --> {}
	 *    15  10                 10                      */      
	public void whenRemoveWithSingleLeftChildOnPushDown_ThenElementsSorted() {
		int[] unsorted= new int[] {15, 50, 10};
		for(int e : unsorted) {
			heap.add(e);
		}
		assertEquals(50, heap.remove());
		assertEquals(15, heap.remove());
		assertEquals(10, heap.remove());
	}

}
