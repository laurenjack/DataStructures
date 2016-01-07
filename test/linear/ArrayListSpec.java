package linear;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListSpec {

	private MyArrayList<Integer> myArrayList;
	
	
	@Test
	public void whenSizeOnNewArrayList_ThenReturns0() {
		myArrayList= new MyArrayList<>();
		
        assertEquals(0, myArrayList.size());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void whenGetWithNegativeIndex_ThenThrows() {
		myArrayList= new MyArrayList<>();
		
		myArrayList.get(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void whenGetEmptyList_ThenThrows() {
		myArrayList= new MyArrayList<>();
		
		myArrayList.get(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void whenGetJustOutsideRange_ThenThrows() {
		myArrayList= new MyArrayList<>();
		myArrayList.add(-1234);
		
		myArrayList.get(1);
	}
	
	@Test
	public void whenAdd_ThenReturnsTrueSizeIncreasesAndAtCorrectIndex() {
	    myArrayList= new MyArrayList<>();
	    
	    boolean result= myArrayList.add(555);
	    
	    assertTrue(result);
	    assertEquals(1, myArrayList.size());
	    assertEquals(555, (int)myArrayList.get(0));
	}
	
	@Test
	public void whenAddNull_ThenReturnsTrueAndSizeIncreases() {
	    myArrayList= new MyArrayList<>();
	    
	    boolean result= myArrayList.add(null);
	    
	    assertTrue(result);
	    assertEquals(1, myArrayList.size());
	}
	
	
	@Test
	public void whenSequentialAddsRequiringResizes_ThenCorrectSizeAndIndiciesMaintained() {
		Integer[] ints= new Integer[] {121212, null, -1001, -54321, 9999};
		
		myArrayList= listResizedState(ints);
		
		assertList(myArrayList, ints);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void whenAddIndexJustOutOfBounds_ThenThrows() {
		myArrayList= listResizedState(121212, null, -1001, -54321, 9999);
		
		myArrayList.add(6, 12345);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void whenAddIndexNegative_ThenThrows() {
		myArrayList= listResizedState(121212, null, -1001, -54321, 9999);
		
		myArrayList.add(-1, 12345);
	}
	
	@Test
	public void whenAddIndexAtEndOfList_ThenSizeAndIndiciesMaintained() {
		myArrayList= listResizedState(121212, null, -1001, -54321, 9999);
		
		myArrayList.add(5, 12345);
		
		assertList(myArrayList, 121212, null, -1001, -54321, 9999, 12345);
	}
	
	@Test
	public void whenContainsOnItemInList_ThenTrue() {
		myArrayList= listResizedState(1212, null, -1001, -54321, 9999);
		
		
	}
	
	
	
	/*A hard coded list (with initial array size 2) to allow quick test setup of a list that has been resized*/
	private  MyArrayList<Integer> listResizedState(Integer ... ints){ 
        MyArrayList<Integer> resizedList =new MyArrayList<>(2);
		
        for(Integer integer : ints) {
        	resizedList.add(integer);
        }
        
        return resizedList;
	}
	
	private void assertList(MyArrayList<Integer> actual, Integer ... expected) {
		assertEquals(expected.length, actual.size());
		for(int i=0; i<expected.length; ++i) {
			assertEquals(expected[i], actual.get(i)); 
		}
	}

}
