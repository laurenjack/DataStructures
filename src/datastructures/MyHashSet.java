package datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<T> implements Set<T> {

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int DEFAULT_CAPACITY = 16;
	private static final int RESIZE_FACTOR = 2;

	private final float loadFactor;
	private int capacity;
	private LinkedNode[] data;
	private int threshold;
	private int size;

	public MyHashSet() {
		this(DEFAULT_CAPACITY);
	}

	public MyHashSet(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	@SuppressWarnings("unchecked")
	public MyHashSet(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("initialCapacity: "
		        + initialCapacity + " must be non-negative");
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("loadFactor: " 
		        + loadFactor + " must be a positive number");
		}
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.threshold = (int) (capacity * loadFactor);
		this.data = new MyHashSet.LinkedNode[capacity];
		this.size = 0;
	}

	@Override
	public boolean add(T e) {
		int i = indexOf(e);
		final LinkedNode first = data[i];
		final LinkedNode newNode = new LinkedNode(e);
		for (LinkedNode node = first; node != null; node = node.next) {
			if (node.e.equals(e)) {
				node.e = e;
				return false;
			}
		}
		if (size >= threshold) {
			resize(threshold);
		}
		data[i] = newNode;
		newNode.next = first;
		++size;
		return true;
	}

	private class LinkedNode {

		private T e;
		private LinkedNode next;

		private LinkedNode(T e) {
			this.e = e;
		}

	}

	/* Find the index in the backing array for e, based on its hash */
	private int indexOf(Object e) {
		int hash = e.hashCode();
		return (hash & data.length - 1);
	}

	@Override
	public boolean addAll(Collection<? extends T> coll) {
		boolean hasChanged = false;
		for (T e : coll) {
			if (add(e)) {
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object e) {
		final int hash = e.hashCode();
		int index = indexOf(hash);
		if (data[index] == null) {
			return false;
		}
		for (LinkedNode node = data[index]; node != null; node = node.next) {
			if (node.e.equals(e)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		for (Object e : coll) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void foo() {

	}

	@SuppressWarnings("unchecked")
	private void resize(int totalThreshold) {
		threshold= RESIZE_FACTOR * totalThreshold;
		capacity = (int) (threshold / loadFactor);
		LinkedNode[] temp = this.data;
		this.data = new MyHashSet.LinkedNode[capacity];
		for (int i = 0; i < temp.length; ++i) {
			if(temp[i]!=null) {
				
			}
			this.data[i] = temp[i];
		}
	}
	
	int getCapacity() {
		return capacity;
	}
	
	int getThreshold() {
		return threshold;
	}

}
