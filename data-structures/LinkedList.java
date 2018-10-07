
//import java.util.Arrays

public class LinkedList<E extends Comparable<E>>{

	
	LinkedListNode<E> head;


	public LinkedListNode<E> search(E key){
		LinkedListNode<E> x = head;
		while(x != null && !x.key.equals(key)){
			x = x.next;
		}
		return x;
	}

	public void insert(E elem){
		LinkedListNode<E> node = new LinkedListNode<E>(elem);
		node.next = head;
		if(head != null){
			head.prev = node;
		}
		head = node;
		node.prev = null;
	}

	public void delete(LinkedListNode<E> node){
		if(node.prev != null){
			node.prev.next = node.next;
		}else{
			head = node.next;
		}
		if(node.next != null){
			node.next.prev = node.prev;
		}
	}

	public void print(){
		LinkedListNode<E> node = head;
		while(node != null){
			System.out.print(node.key + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	
	public static void main (String[] args) {
		LinkedList<Integer> lst = new LinkedList<Integer>();	
		lst.insert(new Integer(6));
		lst.insert(new Integer(5));
		lst.insert(new Integer(2));
		lst.insert(new Integer(8));
		lst.insert(new Integer(7));
		lst.print();
		LinkedListNode<Integer> nde = lst.search(new Integer(2));
		if(nde == null){
			System.out.println("null");
		}else{
			nde.print();
		}
	}

}


