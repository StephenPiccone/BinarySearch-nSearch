
/**
 * 
 * @author Stephen Piccone
 * @author Joshua Dors
 * EECS 2011 E Assignment 2
 *
 */
import java.util.Arrays;

public class Search {

	public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
		if (toIndex >= fromIndex && (fromIndex < a.length - 1)) {

			int middle = fromIndex + (((toIndex - 1) - fromIndex) / 2);

			// debugging
			// System.out.println(middle);

			// Check if the key is the element at the middle of the array
			if (a[middle] == key) {
				return middle;
			}

			// Split array in half by checking if key is larger then middle, if it is search
			// lower half of array
			if (a[middle] > key) {
				return binarySearch(a, fromIndex, middle - 1, key);
			}

			// Split array in half, search upper half of array
			return binarySearch(a, middle + 1, toIndex, key);

		}

		else if (fromIndex < 0 || toIndex > a.length) {
			throw new IllegalArgumentException();
		}

		// Key not in array
		return -1;
	}

	public static int nSearch(int ways, int[] array, int fromIndex, int toIndex, int key) {

		if (ways < array.length && ways > 1 && toIndex >= fromIndex && (fromIndex < array.length)) {

			int mids[]; // good
			mids = new int[ways - 1]; // good
			int mid = fromIndex + (((toIndex - 1) - fromIndex) / ways);
			for (int j = 0; j < mids.length; j++) {
				mids[j] = mid;
				mid += (fromIndex + (toIndex + 1)) / ways;
			}
			// System.out.println("Midpoint(s) are at: " + Arrays.toString(mids));

			for (int i = 0; i < mids.length; i++) {
				if (array[mids[i]] == key) {
					// System.out.println("Midpoint: " + mids[i] + " is the key!");
					return key;
				} else if (array[mids[i]] > key) {
					// System.out.println("Value at midpoint is HIGHER than key");
					return nSearch(ways, array, fromIndex, mids[i] - 1, key);
				} else if (array[mids[i]] < key) {
					// System.out.println("Value at midpoint is LOWER than key");
					return nSearch(ways, array, mids[i] + 1, toIndex, key);
				}
			}

		}

		else if (ways < 0 || fromIndex < 0 || toIndex > array.length) {
			throw new IllegalArgumentException();
		}
		return -1;

	}

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int result = nSearch(8, array, 0, array.length - 1, 7);

		if (result == -1) {
			System.out.println("Key not in array");
		} else {
			System.out.println("Key found at index: " + result);
		}

	}

}
