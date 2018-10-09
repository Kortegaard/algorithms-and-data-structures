package javads.datastructures;

/**
 * Double linked lists.
 *
 * <p>
 *	this class is based on the data structure of double linked list, which simply means that every node
 *	have a pointer to the node before it in the list, and the node after it in the list.
 *
 *	The only thing the LinkedList have is a pointer to the first elements, and the functions is then
 *	based on the next and prev of nodes.
 *
 *	\<E extends Comparable\<E>\>\> is used such that this class can be used for any type, ass long as
 *	we can compare these. The comparing is used for the search function.
 * </p>
 *
 */
public class LinkedList<E extends Comparable<E>>{
	
	/**
	 * The first node in the linked list.
	 */
	LinkedListNode<E> head;

	/**
	 * Searching for a key in the list.
	 *
	 * <p>
	 *	Goes through all the element one by one in the list until the correct key is found
	 *	(if an node with correct key exists).
	 *	This means that the worst case time is O(n)
	 * </p>
	 *
	 * @param key 	key to search for
	 * @return 		the node x with the x.key==key, or null if not found
	 */
	public LinkedListNode<E> search(E key){
		LinkedListNode<E> x = head;
		while(x != null && !x.key.equals(key)){
			x = x.next;
		}
		return x;
	}

	/**
	 * Inserts a new element in the beginning of the list.
	 * Worst case this runs in time O(1)
	 *
	 * @param elem 	element to insert
	 */
	public void insert(E elem){
		// creating node to insert into the list.
		LinkedListNode<E> node = new LinkedListNode<E>(elem);
		// inserts in the beginning
		node.next = head;
		if(head != null){
			head.prev = node;
		}
		head = node;
		node.prev = null;
	}

	/**
	 * Deleting a node from the list.
	 * 
	 * <p>
	 *	Since the node already is given to the function, the deletion
	 *	of this node is simply to correction the pointers of the previous and next
	 *	node in the list to each other, and thus deleation will run in time O(1).
	 *		Notice that inserting this node again is also possible simply by reversing
	 *	what we are doing here (based on no other changes).
	 * </p>
	 *
	 * @param node	node to delete.
	 */
	public void delete(LinkedListNode<E> node){
		// if not first element change pointer of predecessors
		if(node.prev != null){
			node.prev.next = node.next;
		}else{
			head = node.next;
		}
		//If not last element change pointer of the successor
		if(node.next != null){
			node.next.prev = node.prev;
		}
	}

	/**
	 * Printing the list
	 */
	public void print(){
		LinkedListNode<E> node = head;
		while(node != null){
			System.out.print(node.key + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	/**
	 * A combination of a demo and a small test of the class.
	 */
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

