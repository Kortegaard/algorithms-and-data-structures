import java.util.Arrays;

public class QuickSort{

	/**
	 * Quicksort
	 *
	 * @param list	list to sort
	 */
	public static void quickSort(int[] list){
		quickSort(list, 0, list.length-1);
	}

	/**
	 * Sorting subarray list[p..r] using quicksort, with help for partition
	 * 
	 * @param list 	list to sort
	 * @param p     start index for the subarray to sort
	 * @param r     end index for the subarray to sort
	 */
	public static void quickSort(int[] list, int p, int r){
		if(p<r){
			int q = partition(list,p,r);
			quickSort(list,p,q-1);
			quickSort(list,q+1,r);
		}
	}

	/**
	 * Making a partition of a list based on the last element
	 *
	 * @param list 	list to partition
	 * @param p 	first element for the subarray to partition
	 * @param r 	last element for the subarray to partition
	 * @return 		the index where the partition is
	 */
	public static int partition(int[] list, int p, int r){
		// x it the elem that the list will be partitioned in respect to
		int x = list[r];
		// the current index for the 'lesser' partition
		int i = p - 1;
		// j is variable to keep track of how far we have come
		for(int j = p; j <= r-1; j++){
			if(list[j] <= x){
				i++;
				exchange(list, i, j);
			}
		}
		exchange(list, i+1, r);
		return i+1;
	}
	
	/**
	 * Exchanging 2 elements in list
	 *
	 * @param list 	list to exchange in
	 * @param i1 	first index to exchange
	 * @param i2 	second index to exchange
	 */
	public static void exchange(int[] list, int i1, int i2){
		int temp = list[i1];
		list[i1] = list[i2];
		list[i2] = temp;
	}

	public static void main (String[] args) {
		int[] tester =  new int[]{1,5,3,4,2,6,4,34,23,46,4,3,34,61,3,5,34,34,52,2};
		quickSort(tester);
		System.out.println(Arrays.toString(tester));
	}

}
