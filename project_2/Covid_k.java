package project2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Covid_k {

    public static void swap(CityInterface[] array, int i, int j){
        //Method to swap 2 elements in an array
        CityInterface temp= array[i];
        array[i] = array[j];
        array[j] =temp;
    }
    
    public static int partition(CityInterface[] array, int beg, int end){
        
        //Get a random pivot between beg and end
        int random=beg + ((int)Math.random()*(array.length))/(end-beg+1);

        //New position of pivot element
        int last=end;
        
        //Move the pivot element to right edge of the array
        swap(array, random, end);
        end--;
        
        while(beg<=end){
            if(array[beg].compareTo((City)array[last])>0) beg++; //Gather smaller objects to the left
            else {
                swap(array, beg, end);
                end--;
            }
        }
        
        //Move pivot element to its correct position
        swap(array, beg, last);
        
        return beg;
    }
    
    public static void quicksort(CityInterface[] array, int beg, int end){
        if(beg>=end) return;
        if(beg<0) return;
        if(end>array.length-1) return;
        
        int pivot = partition(array, beg, end);
        quicksort(array, beg, pivot-1);
        quicksort(array, pivot+1, end);
    }
	


	public static void main(String[] args) throws IOException {
		
		// Counting lines in our file to initialize the size of the array
		int lineCount = (int) Files.lines(Paths.get(args[1])).count();

		CityInterface[] arrayOfCities = new CityInterface[lineCount];
		City city;
		int i = 0;
		
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
				// variables above
				if ((covidCases <= population) && (name.length() <= 50 && id >= 1 && id <= 999 && population > 0 && population <= 10000000)) {
					city = new City(id, name, population, covidCases);
					arrayOfCities[i]=city;
					i++;
				}
			}
		} catch (IOException e) {
			System.out.println("FAILED READING FILE");
		}
		

		if (i != 0 && Integer.parseInt(args[0]) <= i) {
			quicksort(arrayOfCities,0,arrayOfCities.length-1);

			System.out.println("The top " + args[0] + " cities are :");
			System.out.println();
			for (int j = 0; j < Integer.parseInt(args[0]); j++) {
				System.out.println(arrayOfCities[j].getName());
			}

		} else {
			System.out.println("ERROR  No City  was read or the k-cities that were requested ");
			System.out.println("exceed the total amount of cities that were read .");
		}
	}
	

}
