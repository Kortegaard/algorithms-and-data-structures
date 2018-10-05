import java.util.*;

public class TestMerge{

	public int[] randomIntArray(int size, int min, int max){
		int[] arr = new int[size];
		for(int i = 0; i < size; i++){
			arr[i] = (int) (Math.random() * ( max - min )) + min; 
		}
		return arr;
	}

	public void testMergeSort(){
		int testNumber = 100;
		int minSize = 100000;
		int maxSize = 300000;
		boolean pass = true;
		for(int j = 0; j < testNumber; j++){
			int size = (int) (Math.random() * ( maxSize - minSize )) + minSize; 
			int[] array = randomIntArray(size,0,100000);
			int[] array2 = new int[size];
			System.arraycopy( array, 0, array2, 0, array.length );
			Arrays.sort(array);
			MergeSort merge = new MergeSort();
			merge.mergeSort(array2);
			pass = pass && Arrays.equals(array,array2);
		}
		if(pass)
			System.out.println("MergeSort: SUCCESS");
		else
			System.out.println("MergeSort: FAILED");
	}

	public static void main (String[] args) {
		TestMerge test = new TestMerge();
		test.testMergeSort();
	}

}

