import java.util.Arrays;
import java.util.Random;

public class SearchTester {
	private static Random rng = new Random();

	public static void main(String[] args) {
		int [] array;
		long start, end;
		int valueToSearch; 

		for (int i = 10; i <= 10_000_000; i *= 10){ 
			valueToSearch = 100000;
			int found;
			int ways = 384;
			
			array = createRandomSortedArray(i); 

			start = System.nanoTime();
			found = Arrays.binarySearch(array, valueToSearch);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nTime spent (Arrays.binarySearch): " + (end - start)/1e3 + " us");

			start = System.nanoTime();
			found = Search.binarySearch(array, 0, array.length, valueToSearch);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nTime spent (bin): " + (end - start)/1e3 + " us");

			start = System.nanoTime();
			found = Search.nSearch(30, array, 0, array.length, valueToSearch);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nTime spent ("+ 30 +"-way): " + (end - start)/1e3 + " us\n");
			
			start = System.nanoTime();
			found = Search.nSearch(90, array, 0, array.length, valueToSearch);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nTime spent ("+ 90 +"-way): " + (end - start)/1e3 + " us\n");
			
			start = System.nanoTime();
			found = Search.nSearch(ways, array, 0, array.length, valueToSearch);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nTime spent ("+ ways +"-way): " + (end - start)/1e3 + " us\n");
		}
	}

	private static int [] createRandomSortedArray (int size){
		rng.setSeed(8); //each time, the random sequence will be the same
		int [] array = new int [size];
		
		for (int i = 0; i < size; i++) array[i] = rng.nextInt();
				
		Arrays.sort(array);
		return array;
	}
}
