package javads.arrays;

import java.util.Arrays;

public class MaximumSubarray{
	
	/**
	* Find the maximum subarray sum
	*
	* @param list 	list to find the maxsubsum of
	* @return 		the max sum
	*/
	public static int maxSubarray(int[] list){
		int maxSoFar = 0,maxEndingHere = 0;
		for(int i = 0; i < list.length; i++){
			maxEndingHere 	= Math.max(maxEndingHere + list[i],0);
			maxSoFar 		= Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	public static void main (String[] args) {
		int[] tester = new int[]{3,4,2,0,-2,3,-5,3,6,2-2,4,-30,3,4,2,3,5,3,3};
		System.out.println(maxSubarray(tester));
	}

}
