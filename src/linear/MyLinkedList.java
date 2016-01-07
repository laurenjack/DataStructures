package linear;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements List<E> {

	// a sentinel pointer to simplify boundry conditions
	//private Node pointer = new Node(null);
	private Node start;
	private Node end;
	private int size = 0;

	
	public MyLinkedList() {
		resetList();
	}
	
	class Node {

		private E element;
		private Node prev;
		private Node next;
		
		public Node(E element) {
			this(element, null, null);
		}

		public Node(E element, Node prev, Node next) {
			this.element = element;
			this.prev=prev;
			this.next = next;
		}

	}

	@Override
	public boolean add(E e) {
		Node newNode= new Node(e, end.prev, end);
		end.prev= newNode;
		++size;
		return true;
	}

	@Override
	public void add(int index, E element) {
		validateIndex(index, size + 1);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		resetList();
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) == -1;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (E e : this) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		validateIndex(index);
		int i = 0;
		for (E e : this) {
			if (i == 0) {
				return e;
			}
			++i;
		}
		throw new AssertionError("This line should be unreachable");
	}

	@Override
	public int indexOf(Object o) {
		int i = 0;
		for (E element : this) {
			if (o == null ? element == null : o.equals(element)) {
				return i;
			}
			++i;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {

			private boolean canRemove= false;
			private Node current= start;

			@Override
			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public E next() {
				canRemove= true;
				if (hasNext()) {
					return current.next.element;
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				if (!canRemove) {
					throw new IllegalStateException(
							"Must make a call to next prior to each remove");
				}
				canRemove = false;
				removeNode(current.prev, current);
			}
		};
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	private void removeNode(Node prev, Node node) {
		if (node.next == null) {
			prev.next = null;
		}
		prev.next = node.next;
	}

	private void validateIndex(int index) {
		validateIndex(index, size());
	}

	private void validateIndex(int index, int upperBound) {
		if (index < 0 || index >= upperBound) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size());
		}
	}
	
	/*Clears the list, ressting the start and end pointers */
	private void resetList() {
		start= new Node(null, null, null);
		end= new Node(null, start, null);
		start.prev=end;
	}

}
