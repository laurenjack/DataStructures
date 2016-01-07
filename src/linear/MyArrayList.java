package linear;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements List<E> {

	private static final int INIT_SIZE = 10;
	private static final int RESIZE_CONST = 2;
	private static final int ELEMENT_NOT_FOUND = -1;

	private E[] array;
	private int size = 0;

	public MyArrayList() {
		this(INIT_SIZE);
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(int size) {
		array = (E[]) new Object[size];
	}

	@Override
	public boolean add(E element) {
		if (size() == array.length) {
			resizeIfToBig(array.length);
		}
		array[size++] = element;
		return true;
	}

	@Override
	public void add(int index, E element) {
		validateIndex(index, size() + 1);
		resizeIfToBig(size());
		for (int i = size(); i > index; --i) {
			array[i] = array[i - 1];
		}
		array[index] = element;
		++size;
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		resizeIfToBig(size() + collection.size());
		for (E element : collection) {
			array[size++] = element;
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		validateIndex(index);
		int collSize = collection.size();
		resizeIfToBig(size() + collSize);
		for (int i = collSize + size() - 1; i >= index + collSize; --i) {
			array[i] = array[i - collSize];
		}
		for (int i = size(); i > index; --i) {
			array[i] = array[i - 1];
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		array = (E[]) new Object[INIT_SIZE];
	}

	@Override
	public boolean contains(Object element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object e : collection) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		validateIndex(index);
		return array[index];
	}

	@Override
	public int indexOf(Object element) {
		if (element == null) {
			for (int i = 0; i < size(); ++i) {
				if (array[i] == null) {
					return i;
				}
			}
			return ELEMENT_NOT_FOUND;
		}
		for (int i = 0; i < size(); ++i) {
			if (element.equals(array[i])) {
				return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size();
			}

			@Override
			public E next() {
				if (hasNext()) {
					return get(index++);
				}
				throw new NoSuchElementException(
						"The iterator has no more elements");
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(
						"Iterator.remove is unsupported use ArrayList.remove instead");
			}
		};
	}

	@Override
	public int lastIndexOf(Object arg0) {
		if (isEmpty()) {
			return ELEMENT_NOT_FOUND;
		}
		return size() - 1;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object element) {
		int index = indexOf(element);
		if (index == ELEMENT_NOT_FOUND) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
		validateIndex(index);
		E element = get(index);
		for (int i = index; i < array.length - 1; ++i) {
			array[i] = array[i + 1];
		}
		--size;
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean changed = false;
		for (Object e : collection) {
			if (!changed && remove(e)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		validateIndex(index);
		E previous = get(index);
		array[index] = element;
		return previous;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (size > a.length) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		System.arraycopy(array, 0, a, 0, size);
		for (int i = size; i < a.length; ++i) {
			a[i] = null;
		}
		return a;
	}

	private void resizeIfToBig(int oldSize) {
		if (oldSize < array.length) {
			return;
		}
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[oldSize * RESIZE_CONST];
		for (int i = 0; i < size; ++i) {
			newArray[i] = array[i];
		}
		array = newArray;
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

}
