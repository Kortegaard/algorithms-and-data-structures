package javads.arrays.sorting;

import java.util.Arrays;

public class BubbleSort{
	
	/**
	 * Bubblesorting
	 *
	 * @param list list to be sorted
	 */
	public static void bubbleSort(int[] list){
		int temp;
		for(int i = 0; i < list.length-1; i++){
			for(int j = list.length-1; j > i; j--){
				if(list[j] < list[j-1]){
					// Exchange elems					
					temp 		= list[j-1];
					list[j-1] 	= list[j];
					list[j] 	= temp;
				}
			}
		}
	}
	
	public static void main (String[] args) {
		int[] tester = new int[]{5,4,6,3,8,7,9,1,2,0};
		bubbleSort(tester);
		System.out.println(Arrays.toString(tester));
	}


}
