package javads.datastructures;

public class BinarySearchTreeNode<T extends Comparable<T>>{

	T key;
	
	/**
	 * Fields to navigate in the search tree
	 */
	BinarySearchTreeNode<T> parent;
	BinarySearchTreeNode<T> left;
	BinarySearchTreeNode<T> right;

	/**
	 * Constructor with key
	 */
	public BinarySearchTreeNode(T key){
		this.key = key;
	}

}

