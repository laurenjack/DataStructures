package datastructures;

import static org.junit.Assert.*;

import java.util.Arrays;

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

		Object sameHash1 = mockFixedHashCode();
		Object sameHash2 = mockFixedHashCode();

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
			sameHashes[i]= mockFixedHashCode();
		}
		
		for(Object sameHash : sameHashes) {
			assertTrue(hashSet.add(sameHash));
		}
		
		assertSet(hashSet, sameHashes);
	}
	
	@Test
	public void whenAddThatJustRequiresResize_ThenAllAddedAndCapAndThresholdChanged() {
		MyHashSet<Integer> hashSet= new MyHashSet<>(3);
		
		assertTrue(hashSet.add(-111));
		assertTrue(hashSet.add(222));
		assertTrue(hashSet.add(-333));
		
		assertSet(hashSet, -111, 222, -333);
		assertEquals(4, hashSet.getThreshold());
		assertEquals(5, hashSet.getCapacity());
	}

	/**
	 * Produce an anynomous subclass of object that overrides hashcode, so that
	 * it returns {@link MyHashSetSpec#FIXED_HASH}
	 */
	private Object mockFixedHashCode() {
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
