import java.util.Arrays;

/**
 * @author: Anders Kortegaard
 * @date:	2018-10-5
 */
public class MaxHeap{
	
	int size;
	int[] heap;

	/**
	 * setup the the max heap object
	 *
	 * @param heap an array of ints to fill the heap
	 */
	public MaxHeap(int[] heap){
		this.size = heap.length;
		this.heap = Arrays.copyOf(heap, this.size);
	}

	/**
	 * calculates the parent of an index
	 *
	 * @param 	i - the index of the element of which you want the parent
	 * @return	index of the parent 
	 */
	public int parent(int i){
		return (int) Math.floor((i-1)/2);
	}
	
	/**
	 * calculates the left child of an index
	 *
	 * @param 	i - the index of the element of which you want the child
	 * @return	index of left child of i
	 */
	public int left(int i){
		return 2*i+1;
	}	

	/**
	 * calculates the right child of an index
	 *
	 * @param 	i - the index of the element of which you want the child
	 * @return	index of left child of i
	 */
	public int right(int i){
		return 2*i+2;
	}

	/**
	 * getting value from index
	 *
	 * @param i index in the heap
	 * @return value of the int on the i'th index in the heap
	 */
	public int get(int i){
		return heap[i];
	}

	/**
	 * setting value based on index
	 *
	 * @param i index in the heap
	 */

	public void set(int i, int val){
		heap[i] = val;
	}
	
	/**
	 * Exchanging 2 elements in the heap based on indexes
	 *
	 * @param i index of first element to exhange
	 * @param j index of second element to exhange
	 */
	public void exchange(int i, int j){
		int temp = heap[i];
		this.set(i,heap[j]);
		this.set(j,temp);
	}

	/**
	 * assuming that the two subheaps of index i, is a max-heap
	 * maxHeapify will then make the heap with root i, into a max heap
	 *
	 * @param i index of root of which there will be created a max heap
	 */
	public void maxHeapify(int i){
		int largest;
		int l = left(i);
		int r = right(i);
		if(l < this.size && this.get(l) > this.get(i))
			largest = l;
		else
			largest = i;
		if(r < this.size && this.get(r) > this.get(largest))
			largest = r;
		if(largest != i){
			this.exchange(i,largest);
			this.maxHeapify(largest);
		}
	}
	
	/**
	 * Restructuring the heap into a max-heap
	 */
	public void buildMaxHeap(){
		for(int i = (int )Math.floor(this.size/2);i>=0;i--){
			maxHeapify(i);
		}
	}

	/**
	 * max element in heap
	 *
	 * @return max value in heap
	 */
	public int max(){
		return heap[0];
	}

	/**
	 * Getting max element in the heap and then delete this element
	 *
	 * @return max value in the max-heap
	 */
	public int extractMax(){
		if(this.size < 1)
			return -1;
		int max = get(0);
		this.set(0,this.get(this.size-1));
		this.size--;
		this.maxHeapify(0);
		return max;
	}
	
	/**
	 * replacing a key of greater value. If the value of the new key, is less
	 * than the one already in the heap. This will do nothing
	 *
	 * @param i 	the key for the replacement
	 * @param key	the new key	
	 */
	public void increaseKey(int i, int key){
		if(key < get(i))
			return;
		set(i,key);
		while(i>0 && get(parent(i)) < get(i)){
			exchange(i,parent(i));
			i = parent(i);
		}
	}
	

	/**
	 * inserting key into the heap
	 *
	 * @param key the new key to insert
	 */
	public void insert(int key){
		this.size = heap.length+1;
		this.heap = Arrays.copyOf(heap, this.size+1);
		this.heap[size-1] = key-1;
		increaseKey(size-1,key);
	}

	/**
	 * Printing heap
	 */
	public void printHeap(){
		int height = log2(this.size) + 1;
        for (int i = 0, len = this.size; i < len; i++) {
            int x = heap[i];
            int level = log2(i+1)+1;
            int spaces = (height - level + 1) * 2;
            System.out.print(stringOfSize(spaces, ' '));
            System.out.print(x);
            if((int)Math.pow(2, level) - 2 == i) 
				System.out.println();
        }	
        System.out.println("");
	}

	/**
	 * helping function for the print heap
	 */
	private String stringOfSize(int size, char ch) {
        char[] a = new char[size];
        Arrays.fill(a, ch);
        return new String(a);
    }

	/**
	 * calculates logarithm base 2
	 *
	 * @param 	x number for which log2 will be calculated
	 * @return 	log_2(x)
	 */
	private int log2(int x) {
		return (int)Math.floor((Math.log(x) / Math.log(2))); // = log(x) with base 10 / log(2) with base 10
	}
	
	/**
	 * Heapsort, sorting array based on the heap data structure
	 *
	 * @param	list list to sort
	 * @return 	sorted list
	 */
	public static int[] heapSort(int[] list){
	 	MaxHeap heap = new MaxHeap(list);	
		heap.buildMaxHeap();
		for(int i = heap.size - 1; i>=1 ; i--){
			heap.exchange(0,i);
			heap.size--;
			heap.maxHeapify(0);
		}
		return heap.heap;
	}

	/**
	 * examples of uses
	 */
	public static void main (String[] args) {
		int[] arr = new int[]{1,9,8,12,2,3,6,4,5,5,10};
		MaxHeap heap = new MaxHeap(arr);
		
		//heap.buildMaxHeap();
		//heap.printHeap();
		//heap.increaseKey(5,13);
		//System.out.println();
		//heap.printHeap();
		//heap.insert(11);
		//System.out.println();
		//heap.printHeap();

		//heap.buildMaxHeap();
		//heap.printHeap();
		//System.out.println("\nextract max: "+ heap.extractMax());
		//heap.printHeap();

		//System.out.println("\n------ Heap ------");
		//heap.printHeap();
		//heap.buildMaxHeap();
		//System.out.println("\n\n------ Max Heap ------");
		//heap.printHeap();
		//System.out.println("\n\n------ HEAP SORT ------");
		//System.out.println(Arrays.toString(heapSort(arr))+ "\n");
	}

}

