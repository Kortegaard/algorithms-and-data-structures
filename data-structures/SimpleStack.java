import java.util.Arrays;
import java.util.EmptyStackException;
public class SimpleStack<E>{
	
	/**
	 * full size of the stack
	 */
	int size;

	/**
	 * index of last element
	 */
	int top;

	/**
	 * the array
	 */
	E[] stack;

	/**
	 * Initializing simple stack, with an array
	 *
	 * @param list 	the list to fill the stack with
	 */
	public SimpleStack(E[] list){
		this.size = list.length*2;
		this.top = list.length-1;
		this.stack = Arrays.copyOf(list, this.size);
	}
	
	/**
	 * Checks if stack is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty(){
		return top == -1;
	}
	
	/**
	 * Checks if stack is full
	 *
	 * @return boolean
	 */
	public boolean isFull(){
		return top == size-1;
	}

	/**
	 * Adding element to end of stack
	 *
	 * @param elem element to add to stack
	 */
	public void push(E elem){
		// if list is full then double the list
		if(top == size-1){
			size *= 2;
			stack = Arrays.copyOf(stack, size);
		}
		top++;
		stack[top] = elem;
	}

	/**
	 * removing last element from stack
	 * 
	 * @return the element is removed
	 */
	public E pop(){
		// shorten stack length if stack only is 1/4 full
		if(top <= Math.floor(size/4)){
			size = (int) size/2;
			stack = Arrays.copyOf(stack, size);
		}
		// if empty there is no more elements to remove
		if(isEmpty()){
			throw new EmptyStackException();
		}
		top--;
		return stack[top+1];
	}

	/**
	 * printing stack with "*" for the empty places
	 */
	public void print(){
		for(int i = 0; i<=top; i++){
			System.out.print(stack[i] + " ");
		}
		for(int i = top+1; i< size; i++){
			System.out.print("* ");
		}
		System.out.println();
	}

	public static void main (String[] args) {
		Integer[] tester = new Integer[]{3,5,2};
		SimpleStack<Integer> stack = new SimpleStack<Integer>(tester);
		stack.print();
		stack.push(new Integer(3));
		stack.print();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.print();
		stack.push(new Integer(3));
		stack.push(new Integer(3));
		stack.push(new Integer(3));
		stack.push(new Integer(3));
		stack.push(new Integer(3));
		stack.push(new Integer(3));
		stack.print();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.print();
		stack.pop();
		stack.print();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.print();

	}

}
