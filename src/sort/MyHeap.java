package sort;

import java.util.NoSuchElementException;

public class MyHeap {

	private final int[] data;
	private int end;

	public MyHeap(int capacity) {
		data = new int[capacity + 1];
		end = 0;
	}

	public void add(int e) {
		data[end] = e;
		pushUpTree(end++);
	}

	public int remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("The heap is empty");
		}
		int removed = data[0];
		data[0] = data[--end];
		pushDownTree(0);
		return removed;
	}

	public boolean isEmpty() {
		return end == 0;
	}

	private void pushUpTree(int i) {
		if (i == 0) {
			return;
		}
		int parentIndex = (i - 1) / 2;
		if (data[i] > data[parentIndex]) {
			swap(i, parentIndex);
			pushUpTree(parentIndex);
		}
	}

	private void pushDownTree(int i) {
		int left = i * 2 + 1;
		int right = i * 2 + 2;
		if (left >= end) {
			return; // no more elements below i
		}
		// check to make sure that there is a right child, if so, find the index of the max child
		//otherwise the left child is the only child
		int maxValueIndex= right >= end ? left : maxValueIndex(left, right);
		if (data[i] < data[maxValueIndex]) {
			swap(i, maxValueIndex);
			pushDownTree(maxValueIndex);
		}
	}

	/* Swaps the value at i1 with the value at i2 in the underlying array */
	private void swap(int i1, int i2) {
		int temp = data[i1];
		data[i1] = data[i2];
		data[i2] = temp;
	}

	private int maxValueIndex(int i1, int i2) {
		return data[i1] > data[i2] ? i1 : i2;
	}

}
