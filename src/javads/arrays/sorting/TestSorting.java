package javads.arrays.sorting;

import java.util.*;

public class TestSorting{

	/**
	 * Generating an array of specific size, with integers in the interval [min, max].
	 *
	 * @param size 	the size of the array.
	 * @param min	the minimum size for the ints in the array.
	 * @param max	the maximum size for the ints in the array.
	 * @return 		An array of size 'size' with integers ranging from 'min' to 'max'
	 */
	public int[] randomIntArray(int size, int min, int max){
		int[] arr = new int[size];
		for(int i = 0; i < size; i++){
			arr[i] = (int) (Math.random() * ( max - min )) + min; 
		}
		return arr;
	}

	/**
	 * Creating arrays for the testing and running the sortingTest().
	 *
	 * <p>
	 * The four variables set in the beginning dertermines:
	 * 	- the number of test to run
	 * 	- the minimum size for the ints in the arrays
	 * 	- the maximum size for the ints in the arrays
	 * 	- the length of the array
	 * 	Then it will simple create create arrays of desired size and run sortingTest 
	 * 	on these, until the number of tests have been have passed 'testNumber'.
	 * </p>
	 */
	public void testSorting(){
		int testNumber = 100;
		int minSize = 0;
		int maxSize = 1000;
		int arraySize = 1000;
		TestStructure pass = new TestStructure();
		for(int j = 0; j < testNumber; j++){
			int size = (int) (Math.random() * ( maxSize - minSize )) + minSize; 
			int[] array = randomIntArray(size,0,arraySize);
			sortingTests(array, pass);
		}
		pass.printPass();
	}

	/**
	 * Testing the sorting algorithms, up agains the 'arr'.
	 *
	 * <p>
	 * Sorting arrays using different algorithms, and comparing it to javas sorting 
	 * so this ofcause assumes that javas algorithm is correct :)
	 *
	 * For every time an algorithm is running, the array to sort will be changed back to
	 * what is started out to be, this also means that the algorithms will test on the
	 * same arrays.
	 *
	 * Also notice that even though an algorithm have failed, it will still run the next time
	 * </p>
	 *
	 * @param arr 	The array to sort
	 * @param pass	The structure to keep track of which algorithm have passed 
	 */
	public void sortingTests(int[] arr, TestStructure pass){
		int size = arr.length;
		int[] array2 = new int[size];
		int[] sorted = new int[size];

		System.arraycopy( arr, 0, array2, 0, arr.length );
		System.arraycopy( arr, 0, sorted, 0, arr.length );
		Arrays.sort(sorted);
		
		// running merge sort
		MergeSort testMerge = new MergeSort();
		testMerge.mergeSort(array2);
		pass.mergeSort = pass.mergeSort && Arrays.equals(sorted,array2);

		System.arraycopy( arr, 0, array2, 0, arr.length );
		// running insertion sort
		InsertionSort testInsertion = new InsertionSort();
		testInsertion.insertionSort(array2);
		pass.insertionSort = pass.insertionSort && Arrays.equals(sorted,array2);

		System.arraycopy( arr, 0, array2, 0, arr.length );
		// running bubble sort
		BubbleSort testBubble = new BubbleSort();
		testBubble.bubbleSort(array2);
		pass.bubbleSort = pass.bubbleSort && Arrays.equals(sorted,array2);

		System.arraycopy( arr, 0, array2, 0, arr.length );
		// running quick sort
		QuickSort testQuick = new QuickSort();
		testQuick.quickSort(array2);
		pass.quickSort = pass.quickSort && Arrays.equals(sorted,array2);

	}

	/**
	 * running the tests described above
	 */
	public static void main (String[] args) {
		TestSorting test = new TestSorting();
		test.testSorting();
	}


	/**
	 * A simple structure to keep track of which sorting algorithm have been
	 * correct, and which have not
	 */
	public class TestStructure{		

		boolean mergeSort 		= true;
		boolean quickSort 		= true;
		boolean insertionSort 	= true;
		boolean bubbleSort 		= true;

		/**
		 * Printing which of the algorithms have succeeded and which have failed
		 */
		public void printPass(){
			System.out.println("MERGESORT:     " + ((mergeSort) ? "SUCCEEDED" : "FAILED"));
			System.out.println("QUICKSORT:     " + ((quickSort) ? "SUCCEEDED" : "FAILED"));
			System.out.println("INSERTIONSORT: " + ((insertionSort) ? "SUCCEEDED" : "FAILED"));
			System.out.println("BUBBLESORT:    " + ((bubbleSort) ? "SUCCEEDED" : "FAILED"));
		}
	
	}

}


