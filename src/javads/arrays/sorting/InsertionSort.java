import java.io.*;
import java.util.*;

public class InsertionSort{

	/**
	 * Insertion sorting a list
	 *
	 * @param list the list to sort
	 */
	public static void insertionSort(int[] list){
		int j;
		int key;
		for(int i = 1; i < list.length; i++){
			key = list[i];
			j = i-1;
			while(j >= 0 && list[j] > key){
				list[j+1] = list[j];
				j--;
			}
			list[j+1] = key;
		}
	}
	
	public static void main (String[] args) {
		int[] tester =  new int[]{1,5,3,4,2,6,4,34,23,46,4,3,34,61,3,5,34,34,52,2};
		insertionSort(tester);
		System.out.println(Arrays.toString(tester));
	}

}
