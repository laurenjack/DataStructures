package datastructures;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MyHashSetSpec {

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

		hashSet.add(222);
		hashSet.add(333);
		hashSet.add(222);

		assertSet(hashSet, 222, 333);
	}

	@Test
	public void whenAddTwoUnequalItemsWithSameHashCode_ThenOnlyOccursOnce() {
		MyHashSet<Object> hashSet = new MyHashSet<>();
		
		Object sameHash1= new Object() {
			@Override
			public int hashCode() {
				return 42;
			}
		};
		
		Object sameHash2= new Object() {
			@Override
			public int hashCode() {
				return 42;
			}
		};
		
		Object differentHash= new Object();

		hashSet.add(sameHash1);
		hashSet.add(sameHash2);
		hashSet.add(differentHash);

		assertSet(hashSet, sameHash1, sameHash2, differentHash);
	}

	/**
	 * Assert that the set contains all values in 'elements', and that it is the
	 * same size as 'elements'. Relies on a correct implementation of
	 * {@link MyHashSet#containsAll(java.util.Collection)} and
	 * {@link MyHashSet#size()}
	 */
	private void assertSet(MyHashSet<?> hashSet, Object ... elements) {
		assertTrue(hashSet.containsAll(Arrays.asList(elements)));
		assertEquals(elements.length, hashSet.size());
	}

}
