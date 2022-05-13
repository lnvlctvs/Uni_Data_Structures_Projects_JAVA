package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class DynamicCovid_k_withPQ {

	private City[] heap;
	private int size;
	private int topCapacity;


	public DynamicCovid_k_withPQ(int capacity) {

		// Dynamic PQ
		if (capacity < 1)
			throw new IllegalArgumentException();

			this.topCapacity = capacity + 1;
			this.heap = new City[2*topCapacity];
			this.size = 0;

	}

	public void insert(City city) {
		
		// Dynamic PQ insertion (MEROS Ã)

			if (size == topCapacity - 1) {

				City min = city;
				int k = -1; // flag that if that number is changed then the
							// inserted City will be inserted in the heap
				for (int i = 1; i <= size; i++) {
					if (heap[i].compareDynamic(min) < 0) {

						min = heap[i];
						k = i;

					}
				}
				if (k != -1) {
					heap[k] = city;
					swim(k);
				}
			} else {

				heap[++size] = city;

				swim(size);
			}	
	}


	/*
	 * private void resize(int capacity) {
		City[] copy = new City[capacity];
		for (int i = 1; i <= size; i++)
			copy[i] = heap[i];
		heap = copy;
	} */

	public City remove(int id) {
		City city = null;
		int i = 1;
		while (i <= size) {
			if (id == heap[i].getID()) {

				city = heap[i];
				heap[i] = heap[size];
				heap[size--] = null;
				sink(i);

			}
		}
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
			int result = heap[i].compareDynamic(heap[p]);
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
				if(heap[left].compareDynamic(heap[right] ) < 0 )
					max = right;
				else 
					max = left;
			}
			// If the heap condition holds, stop. Else swap and go on.
			if (heap[i].compareDynamic(heap[max]) >= 0)
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
	
	
	
	
	
	public static void main(String[] args) {
		
		City city;
		
		DynamicCovid_k_withPQ heap = new DynamicCovid_k_withPQ(Integer.parseInt(args[0]));
		
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

				System.out.println("The top " + args[0] + " Cities are :");
				while(!heap.isEmpty()) {
					System.out.println(heap.getMax().getName());
				}
			
			
			
		} catch (IOException e) {
			System.out.println("FAILED READING FILE");
		}

	}

}
