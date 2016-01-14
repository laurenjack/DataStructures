package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapSpec {

	@Test
	public void whenNothingPut_ThenSizeZeroAndIsEmpty() {
		HashMap<Integer, Integer> map= new HashMap<>();
		
		assertTrue(map.isEmpty());
		assertEquals(map.size(), 0);
	}
	
	@Test
	public void whenPutOneElement_ThenHasKeyAndValueAndSize1() {
		HashMap<String, Integer> map= new HashMap<>();
		
		map.put("hello", 333);
		
		assertFalse(map.isEmpty());
		assertEquals(map.size(), 1);
		assertEquals((Integer)333, map.get("hello"));
	}

}
