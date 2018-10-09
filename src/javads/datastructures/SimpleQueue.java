package javads.datastructures;

import java.util.Arrays;
import java.util.EmptyStackException;

public class SimpleQueue<E>{
	
	/**
	 * full size of the stack
	 */
	int size;

	/**
	 * index of first element
	 */
	int head;

	/**
	 * index after last element
	 */
	int tail;

	/**
	 * the array
	 */
	E[] queue;

	/**
	 * Initializing simple queue, with an array
	 *
	 * @param list 	the list to fill the queue with
	 */
	public SimpleQueue(E[] list){
		this.size = list.length*2;
		this.head = 0;
		this.tail = list.length;
		this.queue = Arrays.copyOf(list, this.size);
	}

	/**
	 * checks if queue is empty
	 *
	 * @return boolean
	 */
	public boolean isEmpty(){
		return head == tail;
	}
	
	/**
	 * Checks if queue is full
	 *
	 * @return boolean
	 */
	public boolean isFull(){
		if(tail < head)
			return head - 1 == tail;
		return tail == size-1 && head == 0;
	}

	/**
	 * Adding an element to the end of the queue
	 * 
	 * @param elem 	The element to add to the queue
	 */
	public void enqueue(E elem){
		// doubling size of the queue if full
		if(isFull()){
			changeSize(2*size);
		}
		// Adding the element to the queue
		queue[tail] = elem;
		tail++;
		if(tail >= size){
			tail = 0;
		}
	}

	/**
	 * Removes the first element in the queue
	 *
	 * @return the element that is removed
	 */
	public E dequeue(){
		E elem = queue[head];
		head++;
		if(head >= size){
			head = 0;
		}
		if(length() <= Math.floor(size/4))
			changeSize((int) size/2);
		return elem;
	}

	/**
	 * Returns the length of the queue, this is the number of elements in the queueu
	 */		
	public int length(){
		if(head<=tail)
			return tail-head;
		return size-head + tail;
	}
	
	/**
	 * resizing the queue, used when theres either not enoguh space or when only small amount of space is in use
	 * this will ignore extra element if 'newsize' is less than the length.
	 *
	 * @param size	the new size of the array
	 */
	public void changeSize(int newSize){
		E[] newArr = Arrays.copyOf(queue, newSize);
		int i = head;
		int j = 0;
		while(i != tail && j < newSize) {
			j = ((i >= head) ? i-head : size-head+i);
			newArr[j] = queue[i];
			i++;
			if(i>=size){
				i = 0 ;
			}
		}
		this.queue = newArr;
		head = 0;
		tail = j+1;
		size = newSize;
	}


	/**
	 * printing queue with "*" for the empty places
	 */
	public void print(){
		if(tail < head){
			for(int i = 0; i < tail; i++){
				System.out.print(queue[i] + " ");
			}
			for(int i = tail; i < head; i++){
				System.out.print("* ");
			}
			for(int i = head; i < size; i++){
				System.out.print(queue[i] + " ");
			}
		}else{
			for(int i = 0; i < head; i++){
				System.out.print("* ");
			}
			for(int i = head; i < tail; i++){
				System.out.print(queue[i] + " ");
			}
			for(int i = tail; i < size; i++){
				System.out.print("* ");
			}
		}
		System.out.println();
	}


	public static void main (String[] args) {
		Integer[] tester = new Integer[]{3,5,2};
		SimpleQueue<Integer> queue = new SimpleQueue<Integer>(tester);
		queue.print();
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(new Integer(4));
		queue.enqueue(new Integer(4));
		queue.enqueue(new Integer(4));
		queue.enqueue(new Integer(3));
		queue.print();
		queue.enqueue(new Integer(7));
		queue.print();
		queue.dequeue();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
	}

}
