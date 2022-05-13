package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Dynamic_Median {
public static void addSong(City city,PQ  lowers, MinPQ  highers){
		
		if(lowers.size() == 0 || city.compareTo(lowers.max()) < 0){
			lowers.insert(city);
		}else{
			highers.insert(city);
		}
		
	}
	
	public static void rebalance(PQ lowers,MinPQ  highers){
		if(lowers.size() > highers.size()){
			PQ biggerHeap = lowers;
			MinPQ smallerHeap = highers;
			
			if(biggerHeap.size() - smallerHeap.size() >= 2){
				smallerHeap.insert(biggerHeap.getMax());
			}
		}else if(lowers.size() < highers.size()){
			PQ smallerHeap = lowers;
			MinPQ biggerHeap = highers;
			
			if(biggerHeap.size() - smallerHeap.size() >= 2){
				smallerHeap.insert(biggerHeap.getMax());
			}
			
		}
		
		
	}
	
	public static City median(PQ  lowers,MinPQ  highers){
		if(lowers.size() > highers.size()){
			return lowers.max();
		}else {
			return highers.max();
		}
	}

	public static void main(String[] args) {
		City city;

		PQ  lowers = new PQ(251);
		MinPQ  highers = new MinPQ(251);
		int i = 0;
		City median = null;

		try (Scanner scanner = new Scanner(new FileInputStream(args[0]));) {
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
				if ((covidCases <= population)
						&& (name.length() <= 50 && id >= 1 && id <= 999 && population > 0 && population <= 10000000)) {
					city = new City(id, name, population, covidCases);
					addSong(city, lowers, highers);
					rebalance(lowers, highers);
					median = median(lowers,highers);
				}
				
				i++;
				if(i%5 == 0) {
					System.out.println("Median = "+ median.getName());
				}
			}
			
			System.out.println("Final Median is : "+median.getName());

		} catch (IOException e) {
			System.out.println("FAILED READING FILE");
		}

	}

}
