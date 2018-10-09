package javads.datastructures;

public class BinarySearchTree<T extends Comparable<T>>{
	
	BinarySearchTreeNode<T> root = null;

	/**
	 * Inserting node based on element
	 *
	 * @param val value to insert
	 */
	public void insert(T val){
		insert(new BinarySearchTreeNode<T>(val));
	}

	/**
	 * Inserting node,
	 * <p>
	 *	Going down the tree til a leaf is found where the new node can be places
	 *	Then inserting node as the child of the found leaf
	 * </p>
	 * @param node The node to insert
	 */
	public void insert(BinarySearchTreeNode<T> node){
		BinarySearchTreeNode<T> y = null;
		BinarySearchTreeNode<T> x = root;
			
		// Find the place to insert node, by going down the tree
		while(x != null){
			y = x;
			if(node.key.compareTo(x.key) <= 0)
				x = x.left;
			else
				x = x.right;
		}
		node.parent = y;
		if(y == null) // if the new node is the root, set root
			root = node;
		else if(node.key.compareTo(y.key) <= 0) // Setting y's child, if 'node' is not the root 
			y.left = node;
		else
			y.right = node;
	}
	
	/**
	 * An inorder walk from least to greatest
	 *
	 * @param startNode	Node to start the walk
	 */
	public void inorderWalk(BinarySearchTreeNode<T> startNode){
		if(startNode != null){
			inorderWalk(startNode.left);
			System.out.print(" " + startNode.key + " ");
			inorderWalk(startNode.right);
		}
	}

	/**
	 * An preorder walk
	 *
	 * @param startNode	Node to start the walk
	 */
	public void preorderWalk(BinarySearchTreeNode<T> startNode){
		if(startNode != null){
			System.out.print(" " + startNode.key + " ");
			inorderWalk(startNode.left);
			inorderWalk(startNode.right);
		}
	}

	/**
	 * An postorder walk
	 *
	 * @param startNode	Node to start the walk
	 */
	public void postorderWalk(BinarySearchTreeNode<T> startNode){
		if(startNode != null){
			inorderWalk(startNode.left);
			inorderWalk(startNode.right);
			System.out.print(" " + startNode.key + " ");
		}
	}

	/**
	 * Inorder walk, from root
	 */
	public void inorderWalk(){
		inorderWalk(root);
	}

	/**
	 * Preorder walk, from root
	 */
	public void preorderWalk(){
		preorderWalk(root);
	}

	/**
	 * Postorder walk, from root
	 */
	public void postorderWalk(){
		postorderWalk(root);
	}
	
	/**
	 * Searching the search tree
	 * runs in O(h), where h is the hight of the tree
	 *
	 * @param key	the key to search for
	 * @return		search result
	 */
	public BinarySearchTreeNode<T> search(T key){
		BinarySearchTreeNode<T> temp = root;
		while(temp != null && !temp.key.equals(key)){
			if(key.compareTo(temp.key) < 0)
				temp = temp.left;
			else
				temp = temp.right;
		}
		return temp;
	}

	/**
	 * finding smallest key in tree
	 *
	 * @param node 	starting node of the subtree to find minimum
	 * @return minimum key
	 */
	public BinarySearchTreeNode<T> minimum(BinarySearchTreeNode<T> node){
		BinarySearchTreeNode<T> temp = node;
		// Going down left side of tree until minimum key found
		while(temp.left != null){
			temp = temp.left;
		}
		return temp;
	}

	/**
	 * Finding minimum from the root
	 * @return minimum element
	 */
	public BinarySearchTreeNode<T> minimum(){
		return minimum(root);
	}

	/**
	 * finding maximum key in tree
	 *
	 * @param node 	starting node of the subtree to find maximum
	 * @return maximum key
	 */
	public BinarySearchTreeNode<T> maximum(BinarySearchTreeNode<T> node){
		BinarySearchTreeNode<T> temp = node;
		// Going down right side of tree until maximum key found
		while(temp.right != null){
			temp = temp.right;
		}
		return temp;
	}
	
	/**
	 * Finding maximum from the root
	 * @return maximum element
	 */
	public BinarySearchTreeNode<T> maximum(){
		return maximum(root);
	}

	/**
	 * Finding the successor to a node, that is the element with the smallest larger key.
	 * <p>
	 * If the node have a right element, we will have that the successor is in this subtree, and need just
	 * to find the smallest key in that subtree, which we can do with minimum
	 *
	 * If not, we need to go to parent. 
	 * - If the node is a left element of parent, then the parent is the succesoor
	 * - if the node is a right element of parent, then we will have to go up the
	 *   tree until either the root or til we find a parent of which we are
	 *   the left child (thus this parent is greater that the original key)
	 * </p>
	 *
	 * @param node	node to find successor to
	 * @return 		successor
	 */
	public BinarySearchTreeNode<T> successor(BinarySearchTreeNode<T> node){
		if(node.right != null)
			return minimum(node.right);
		BinarySearchTreeNode<T> temp = node;
		BinarySearchTreeNode<T> temp2 = node.parent;
		System.out.println("here");
		while(temp2 != null && temp.equals(temp2.right)){
			temp = temp2;
			temp2 = temp2.parent;
		}
		return temp2;
	}

	/**
	 * removing the subtree of 'node' and replace is with the subtree of 'replace
	 *
	 * @param node	 		root of subtree to replace
	 * @param replacement	root of subtree to put in nodes place
	 *
	 */
	public void transplant(BinarySearchTreeNode<T> node, BinarySearchTreeNode<T> replacement){
		// changing the cild of the parent
		// if node is the root, make the replacement the root
		if(node.parent == null)
			root = replacement;
		else if(node.equals(node.parent.left)) // if the node is a left child
			node.parent.left = replacement;
		else // then node is a right child
			node.parent.right = replacement;
		
		//Changing the parent of the replacement
		if(replacement != null)
			replacement.parent = node.parent;
	}


	/**
	 * Deleting a node from tree.
	 *
	 * <p>
	 * There are three scenarios
	 * 1) no right child: 
	 * 		since no right subtree to consider, just transplant
	 * 		the node with the left subtree.
	 * 2) no left child
	 * 		the same as right, just transplant node with right subtree
	 * 3) both right and left child:
	 * 		in this scenario we will the node we will put in it's place is the minimum node
	 * 		in the right subtree. choosing this the left subtree will still be intact, and the
	 * 		right subtree will all still be greater than this node.
	 * </p>
	 *
	 * @param node 	node to delete
	 */
	public void delete(BinarySearchTreeNode<T> node){
		if(node.right == null)
			transplant(node, node.left);
		else if(node.left == null)
			transplant(node, node.right);
		else{
			BinarySearchTreeNode<T> min = minimum(node.right);
			// if the minimum node's parent is node, then we just need to transplant, and change left
			// else we need to change few more things
			if(!min.parent.equals(node)){
				// replacing y with its right subtree 
				transplant(min, min.right);
				// setting min in between 'node' and its right subtree
				min.right = node.right;
				min.right.parent = min;
			}
			transplant(node, min);
			// setting 'node's left subtree to min's left subtree
			min.left 		= node.left;
			min.left.parent = node;
		}
	}

	/**
	 * Examples of the using the binarytree
	 */
	public static void main (String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(new Integer(4));
		tree.insert(new Integer(5));
		tree.insert(new Integer(3));
		tree.insert(new Integer(9));
		tree.insert(new Integer(8));
		tree.insert(new Integer(2));
		System.out.print("inOrder walk: \t");tree.inorderWalk();
		System.out.println();
		System.out.print("preOrder walk: \t");tree.preorderWalk();
		System.out.println();
		System.out.print("postOrder walk: ");tree.postorderWalk();
		System.out.println();
		System.out.print("Found 9: ");System.out.println(tree.search(new Integer(9)) != null);
		System.out.print("Found 3: ");System.out.println(tree.search(new Integer(3)) != null);
		System.out.print("Found 7: ");System.out.println(tree.search(new Integer(7)) != null);
		System.out.print("min: ");System.out.println(tree.minimum().key);
		System.out.print("max: ");System.out.println(tree.maximum().key);
		System.out.print("Successor to 5: ");System.out.println(tree.successor(tree.search(new Integer(2))).key);
		tree.delete(tree.search(new Integer(5)));
		tree.delete(tree.search(new Integer(8)));
		System.out.print("inOrder walk: \t");tree.inorderWalk();
		System.out.println();
		System.out.print("preOrder walk: \t");tree.preorderWalk();
		System.out.println();
		System.out.print("postOrder walk: ");tree.postorderWalk();
		System.out.println();

	}

}

