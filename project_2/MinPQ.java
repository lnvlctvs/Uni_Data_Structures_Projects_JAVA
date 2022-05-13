package project2;

public class MinPQ {
	
	private City[] heap;
	private Integer[] indexArray;
	private int size;
	private int topCapacity;


	public MinPQ(int capacity) {

		if (capacity < 1)
			throw new IllegalArgumentException();

		// Simple MinPQ
		
			this.topCapacity = capacity + 1;
			this.heap = new City[topCapacity];
			this.indexArray = new Integer[1000]; 
			this.size = 0;
	}

	public void insert( City city) {
		

		
			if (city == null)
				throw new IllegalArgumentException();

			if (size >= ((heap.length - 1) * 75 / 100)) {
				if (size != 0) {
					resize(2 * size + 1);
				} else {
					resize(2);
				}
			}
			
			heap[++size] = city;
			indexArray[city.getID()] = size;
			
			swim(size);
		
	}


	private void resize(int capacity) {
		City[] copy = new City[capacity];
		for (int i = 1; i <= size; i++)
			copy[i] = heap[i];
		heap = copy;
	}
	
	// remove element with specific id() in O(logN) worst case
	public City remove(int id) {
		
		if (indexArray[id] == null || (id < 1 && id > 999))
			throw new IllegalArgumentException();	
		
		City city = null;
		
		// If the element to be removed is the root
		if(indexArray[id] == 1){
			
			city = getMax();
			indexArray[id]=null;
			
		}else {
			
			city = heap[indexArray[id]];
			heap[indexArray[id]] = heap[size];
			heap[size--] = null;
			sink(indexArray[id]);
			
			indexArray[id]=null;
			
		}
		return city;
		
		
	}
	// ACTUAL getMin METHOD
	public City getMax() {

		if (size == 0)
			throw new IllegalStateException();
		// Keep a reference to the root object
		City city = heap[1];
		// Replace root object with the one at rightmost leaf
		if (size > 1)
			heap[1] = heap[size];
		// Dispose the rightmost leaf
		heap[size--] = null;
		// Sink the new root element
		sink(1);

		return city;
	}

	public City max() {
		return heap[1];
	}

	private void swim(int i) {
		while (i > 1) {
			int p = i / 2;
			int result = -heap[i].compareTo(heap[p]);
			if (result <= 0)
				return;
			swap(i, p);
			i = p;
		}
	}

	/**
	 * Shift down.
	 */
	private void sink(int i) {
		int left = 2 * i, right = left + 1, max = left;
		// If 2*i >= size, node i is a leaf
		while (left <= size) {
			// Determine the largest children of node i
			if (right <= size) {
				if(-heap[left].compareTo(heap[right] ) < 0 )
					max = right;
				else 
					max = left;
			}
			// If the heap condition holds, stop. Else swap and go on.
			if (-heap[i].compareTo(heap[max]) >= 0)
				return;
			swap(i, max);
			i = max;
			left = 2 * i;
			right = left + 1;
			max = left;
		}
	}

	/**
	 * Interchanges two array elements.
	 */
	private void swap(int i, int j) {
		City tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	public void print() {
		for (int i = 1; i <= size; i++) {
			System.out.print(heap[i].getName() + " ");
		}
		System.out.println();
	}

	boolean isEmpty() {
		return size == 0;
	}
	
	public int size(){
		return size;
	}

}
