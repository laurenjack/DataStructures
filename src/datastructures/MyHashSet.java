package datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<T> implements Set<T> {

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int DEFAULT_CAPACITY = 16;
	private static final int RESIZE_FACTOR = 2;

	private final float loadFactor;
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
			throw new IllegalArgumentException("loadFactor: " + loadFactor
					+ " must be a positive number");
		}
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}
		this.loadFactor = loadFactor;
		this.threshold = (int) (capacity * loadFactor);
		this.data = new MyHashSet.LinkedNode[capacity];
		this.size = 0;
	}

	@Override
	public boolean add(T e) {
		int i = indexOf(e, data.length);
		final LinkedNode first = data[i];
		final LinkedNode newNode = new LinkedNode(e);
		for (LinkedNode node = first; node != null; node = node.next) {
			if (node.e.equals(e)) {
				node.e = e;
				return false;
			}
		}
		if (size++ >= threshold) {
			resize(RESIZE_FACTOR * data.length);
		}
		i= indexOf(e, data.length);
		newNode.next = data[i];
        data[i]= newNode;
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
	private int indexOf(Object e, int length) {
		int hash = e.hashCode();
		return (hash & length - 1);
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
		int index = indexOf(hash, data.length);
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
	private void resize(int newCapacity) {
		threshold = (int) (newCapacity * loadFactor);
		LinkedNode[] temp = this.data;
		this.data = new MyHashSet.LinkedNode[newCapacity];
		// transfer to new indicies in the larger backing array
		for (int i = 0; i < temp.length; ++i) {
			transferSingleNode(i, temp);
		}
	}

	/* Transfers a single node temp[i] into the backing array */
	private void transferSingleNode(int i, LinkedNode[] temp) {
		LinkedNode n = temp[i];
		if (n != null) {
			temp[i] = null;
			
			while(n!= null) {
				LinkedNode next= n.next;
				int newIndex = indexOf(n, data.length);
				n.next = data[newIndex];
				data[newIndex] = n;
				n= next;
			}
		}
	}

	int getCapacity() {
		return this.data.length;
	}

	int getThreshold() {
		return threshold;
	}

}
