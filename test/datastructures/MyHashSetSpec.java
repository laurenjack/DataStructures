package datastructures;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

public class MyHashSetSpec {

	private int FIXED_HASH = 42;

	@Test
	public void whenIsEmptyOnEmptySet_ThenTrue() {
		MyHashSet<Integer> hashSet = new MyHashSet<>();

		assertTrue(hashSet.isEmpty());
	}

	@Test
	public void whenOneItem_ThenIsEmptyFalse() {
		MyHashSet<Integer> hashSet = new MyHashSet<>();

		hashSet.add(3);

		assertFalse(hashSet.isEmpty());
	}

	@Test
	public void whenAddSingleItem_ThenContainsTrueSize1() {
		MyHashSet<Integer> hashSet = new MyHashSet<>();

		hashSet.add(-101);

		assertTrue(hashSet.contains(-101));
		assertEquals(1, hashSet.size());
	}

	@Test
	public void whenAddTwoEqualItems_ThenOnlyOccursOnce() {
		MyHashSet<Integer> hashSet = new MyHashSet<>();

		assertTrue(hashSet.add(222));
		assertTrue(hashSet.add(333));
		assertFalse(hashSet.add(222));

		assertSet(hashSet, 222, 333);
	}

	@Test
	public void whenAddTwoUnequalItemsWithSameHashCode_ThenBothAdded() {
		MyHashSet<Object> hashSet = new MyHashSet<>();

		Object sameHash1 = mockHashCode(FIXED_HASH);
		Object sameHash2 = mockHashCode(FIXED_HASH);

		Object differentHash = new Object();

		assertTrue(hashSet.add(sameHash1));
		assertTrue(hashSet.add(sameHash2));
		assertTrue(hashSet.add(differentHash));

		assertSet(hashSet, sameHash1, sameHash2, differentHash);
	}
	
	@Test
	public void whenAddMultipleUnequalItemWithSameHashCode_ThenAllAdded() {
		MyHashSet<Object> hashSet = new MyHashSet<>();
		Object[] sameHashes= new Object[10];
		for(int i=0; i<sameHashes.length; ++i) {
			sameHashes[i]= mockHashCode(FIXED_HASH);
		}
		
		for(Object sameHash : sameHashes) {
			assertTrue(hashSet.add(sameHash));
		}
		
		assertSet(hashSet, sameHashes);
	}
	
	@Test
	public void whenAddThatJustRequiresResize_ThenAllAddedAndCapAndThresholdChanged() {
		MyHashSet<Integer> hashSet= new MyHashSet<>(4);
		
		assertTrue(hashSet.add(-111));
		assertTrue(hashSet.add(222));
		assertTrue(hashSet.add(-333));
		assertTrue(hashSet.add(444));
		
		assertSet(hashSet, -111, 222, -333, 444);
		assertEquals(6, hashSet.getThreshold());
		assertEquals(8, hashSet.getCapacity());
	}
	
	@Test
	public void whenAddThatEdgeOfNotNeedingResize_ThenAllAddedButNoCapAndThresholdChanged() {
		MyHashSet<Integer> hashSet= new MyHashSet<>(4);
		
		assertTrue(hashSet.add(-111));
		assertTrue(hashSet.add(222));
		assertFalse(hashSet.add(222));
		assertTrue(hashSet.add(-333));
		assertFalse(hashSet.add(-111));
		
		assertSet(hashSet, -111, 222, -333);
		assertEquals(3, hashSet.getThreshold());
		assertEquals(4, hashSet.getCapacity());
	}
	
	@Test
	public void whenHashIterator_ThenElementsReturnedInSetAndHasNextTrueWhenElementsLeft() {
		MyHashSet<Integer> hashSet= new MyHashSet<>();
		assertTrue(hashSet.add(1234));
		assertTrue(hashSet.add(-5678));
		assertTrue(hashSet.add(910));
		
		Iterator<Integer> itr= hashSet.iterator();
		for(int i=0; i<3; ++i) {
			assertTrue(itr.hasNext());
			assertTrue(hashSet.contains(itr.next()));
		}
		assertFalse(itr.hasNext());
		
	}
	
	@Test
	public void whenIterateWithDeliberateNodesAtStartAndEndOFBackingArray() {
		MyHashSet<Integer> hashSet= new MyHashSet<>(4);
		assertTrue(hashSet.add(3));
		assertTrue(hashSet.add(0));
		
		Iterator<Integer> itr= hashSet.iterator();
		for(int i=0; i<2; ++i) {
			assertTrue(itr.hasNext());
			assertTrue(hashSet.contains(itr.next()));
		}
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void whenIterateWithDeliberateListAndAtStartAndEndOFBackingArray() {
		MyHashSet<Object> hashSet= new MyHashSet<>(8);
		assertTrue(hashSet.add(7));
		assertTrue(hashSet.add(mockHashCode(7)));
		assertTrue(hashSet.add(0));
		assertTrue(hashSet.add(mockHashCode(0)));
		
		Iterator<Object> itr= hashSet.iterator();
		for(int i=0; i<4; ++i) {
			assertTrue(itr.hasNext());
			assertTrue(hashSet.contains(itr.next()));
		}
		assertFalse(itr.hasNext());
	}

	/**
	 * Produce an anynomous subclass of object that overrides hashcode, so that
	 * it returns the provided hashCode
	 * 
	 * @param hachCode - the hashCode for the mock to return
	 */
	private Object mockHashCode(int hashCode) {
        return new Object() {
			@Override
			public int hashCode() {
				return FIXED_HASH;
			}
		};
	}
	

	/**
	 * Assert that the set contains all values in 'elements', and that it is the
	 * same size as 'elements'. Relies on a correct implementation of
	 * {@link MyHashSet#containsAll(java.util.Collection)} and
	 * {@link MyHashSet#size()}
	 */
	private void assertSet(MyHashSet<?> hashSet, Object... elements) {
		assertTrue(hashSet.containsAll(Arrays.asList(elements)));
		assertEquals(elements.length, hashSet.size());
	}

}
