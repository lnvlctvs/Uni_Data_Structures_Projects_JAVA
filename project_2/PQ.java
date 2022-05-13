package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;



public class PQ {
	
	private City[] heap;
	private Integer[] indexArray;
	private int size;
	private int topCapacity;


	public PQ(int capacity) {

		if (capacity < 1)
			throw new IllegalArgumentException();

		// Simple PQ
		
			this.topCapacity = capacity + 1;
			this.heap = new City[topCapacity];
			this.indexArray = new Integer[1000]; 
			this.size = 0;
	}

	public void insert( City city) {
		
		// Simple PQ insertion MEROS B - O(logN)
		
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
		
		city = heap[indexArray[id]];
		int pointer = indexArray[id];
		swap(indexArray[id],size);
		heap[size--] = null;
		indexArray[id] = null;
		
		swim(pointer);
		sink(pointer);
		
		return city;
		
		
		
		
	}
	// ACTUAL getMax METHOD
	public City getMax() {

		if (size == 0)
			throw new IllegalStateException();
		// Keep a reference to the root object
		City city = heap[1];
		// Replace root object with the one at rightmost leaf
		if (size > 1)
			swap(1, size);
		// Dispose the rightmost leaf
		heap[size--] = null;
		// Sink the new root element
		sink(1);
		
		indexArray[city.getID()] = null;

		return city;
	}

	public City max() {
		return heap[1];
	}

	private void swim(int i) {
		while (i > 1) {
			int p = i / 2;
			int result = heap[i].compareTo(heap[p]);
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
				if(heap[left].compareTo(heap[right] ) < 0 )
					max = right;
				else 
					max = left;
			}
			// If the heap condition holds, stop. Else swap and go on.
			if (heap[i].compareTo(heap[max]) >= 0)
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
		indexArray[heap[i].getID()] = i;
		indexArray[heap[j].getID()] = j;
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

	
	
	
	public static void main(String[] args) {
		
		City city;
		
		PQ heap = new PQ(Integer.parseInt(args[0]));
		
		try (Scanner scanner = new Scanner(new FileInputStream(args[1]));) {
			// Reading lines one by one and storing the int's and String's in variables		
			while (scanner.hasNextInt()) {
				int id = scanner.nextInt();
				String name = "";
				while (!scanner.hasNextInt()) {
					name += scanner.next() + " ";
				}
				int population = scanner.nextInt();
				int covidCases = scanner.nextInt();
				
				// Creating City objects based on the info that was read and was stored in the
				// variables above. 
				if ((covidCases <= population) && (name.length() <= 50 && id >= 1 && id <= 999 && population > 0 && population <= 10000000)) {
					city = new City(id, name, population, covidCases);
					heap.insert(city);

				}
			}

			int i = 0;
			System.out.println("The top " + args[0] + " Cities are :");
			while(i < Integer.parseInt(args[0])) {
				System.out.println(heap.getMax().getName());
				i++;
			}
			
			
		} catch (IOException e) {
			System.out.println("FAILED READING FILE");
		}

	}

}
