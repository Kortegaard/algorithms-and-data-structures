
import java.util.Arrays;

public class LinkedListNode<E extends Comparable>{
	
	E key;

	LinkedListNode<E> prev = null;

	LinkedListNode<E> next = null;

	public LinkedListNode(E elem){
		key = elem;
	}
	
	public void print(){
		System.out.println(key);
	}

	public LinkedListNode(E elem, LinkedListNode<E> prev, LinkedListNode<E> next){
		key = elem;
		this.prev = prev;
		this.next = next;
	}

}
