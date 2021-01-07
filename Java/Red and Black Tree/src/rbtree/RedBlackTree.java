package rbtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//import tree.BSTNode;

//import tree.RBNode;

public class RedBlackTree<E extends Comparable<E>> {

	private RBNode<E> root;
	private RBNode<E> NIL;

	public RedBlackTree() {
		this.root = null;
	}

	public RedBlackTree(E[] myArray) {

		this.root = new RBNode(myArray[0]);
		insertCleanUp(root);
		RBNode<E> current = root;
		RBNode<E> temp = current;

		for (int i = 0; i < myArray.length; i++) {

			if (i == 0) {
				current.data = myArray[i];
				current.parent = null;
			}

			else {
				insert(myArray[i]);
			}

		}
	}

	/**
	 * Finds the spot of the parent of the node we will be inserting into the tree.
	 * 
	 * @param key
	 *            is the data inside the node.
	 * @return the parent of the new node we are going to insert.
	 */
	public RBNode<E> insertionPoint(E key) {

		RBNode<E> current = root;

		RBNode<E> parent = null;

		RBNode<E> temp = current;

		while (current != null) {
			if (key.compareTo(current.data) == 0) { // throw
				// DuplicateItemException
			}

			else if (key.compareTo(current.data) < 0) {

				// key<current.data

				parent = current;
				current = current.left;

			}

			else if (key.compareTo(current.data) > 0) {

				// key>current.data

				parent = current;
				current = current.right;

			}

		}

		return parent;

	}

	/**
	 * Inserts a node into the red and black tree.
	 * 
	 * @param key
	 *            is the data which will be inside of the new node.
	 */

	public void insert(E key) {

		RBNode<E> child = new RBNode(key);
		child.left = NIL;
		child.right = NIL;

		if (root == null) {
			insertCleanUp(child);
			root = child;
		} else {

			if (!find(key)) {

				child.parent = insertionPoint(key);

				if (key.compareTo(child.parent.data) < 0) {
					child.parent.left = child;
				} else if (key.compareTo(child.parent.data) > 0) {
					child.parent.right = child;
				}

			}
			insertCleanUp(child);
		}
	}

	/**
	 * Finds if the data exists in red black tree based off of parameter.
	 * 
	 * @param key
	 *            is the data being searched in the tree.
	 * @return true if key is found, and false if it is not found.
	 */

	public boolean find(E key) {

		RBNode<E> current = this.root;

		while (current != null) {

			if (key.compareTo(current.data) == 0) {
				return true;
			}

			else if (key.compareTo(current.data) < 0) {

				current = current.left;
			}

			else if (key.compareTo(current.data) > 0) {

				current = current.right;
			}

			// System.out.println(current);
		}

		return false;
	}

	/**
	 * Checks to see if a tree is empty or not.
	 * 
	 * @return true if tree is empty. False if tree is not empty.
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Checks to see if a node is a leaf.
	 * 
	 * @param node
	 *            is the node we are checking.
	 * @return true if the node is a leaf, and false if it is not a leaf.
	 */

	public boolean isLeaf(RBNode<E> node) {

		if (node.right == null && node.left == null) {
			return true;
		}

		return false;
	}

	/**
	 * Prints the tree.
	 */
	public void printTree() {

		if (this.root.right != null) {
			this.printTree(this.root.right, true, "");
		}

		printNodeValue(this.root);

		if (this.root.left != null) {
			this.printTree(this.root.left, false, "");
		}
	}

	/**
	 * Prints the tree
	 * 
	 * @param node
	 *            is the current node.
	 * @param isRight
	 *            boolean for if it is right.
	 * @param indent
	 */
	private void printTree(RBNode<E> node, boolean isRight, String indent) {
		if (node.right != null) {
			printTree(node.right, true, indent + (isRight ? "        " : " |      "));
		}

		System.out.print(indent);

		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("----- ");
		printNodeValue(node);
		if (node.left != null) {
			printTree(node.left, false, indent + (isRight ? " |      " : "        "));
		}

	}

	/**
	 * Prints the value in the node.
	 * 
	 * @param node
	 *            the current node.
	 */

	private void printNodeValue(RBNode<E> node) {
		if (node == null) {
			System.out.print("<null>");
		} else {
			System.out.print(node.getData() + "(" + node.color + ")");
		}

		System.out.println();
	}

	/**
	 * Counts the number of children of a node in the red black tree.
	 * 
	 * @param node
	 *            is the node we are checking.
	 * @return the count of the number of children that the node has.
	 */

	public int numChildren(RBNode<E> node) {
		int count = 0;

		if (node.left != null) {
			count++;
		}
		if (node.right != null) {
			count++;
		}
		return count;

	}

	/**
	 * Finds the node that will be deleted.
	 * 
	 * @param key
	 *            is the data inside that node.
	 * @return the node if it exists, and null if it does not.
	 */
	public RBNode<E> nodeToDelete(E key) {

		if (find(key)) {
			RBNode<E> current = root;

			while (current != null) {

				if (key.compareTo(current.data) == 0) {

					return current;
				} else if (key.compareTo(current.data) < 0) {

					current = current.left;
				} else if (key.compareTo(current.data) > 0) {
					current = current.right;
				}

			}
		}
		return null;
	}

	/**
	 * A helper method to delete a node.
	 * 
	 * @param key
	 *            is the value of the node that is going to be deleted.
	 */
	public void delete(E key) {

		if (find(key)) {
			RBNode<E> removing = nodeToDelete(key);
			delete(removing);
		}
	}

	/**
	 * Deletes the node.
	 * 
	 * @param node
	 *            is the node that is going to be deleted.
	 */

	public void delete(RBNode<E> node) {

		RBNode<E> child = null;

		if (isLeaf(node)) {
			if (isLeftChild(node)) {
				node.parent.left = null;

			} else if (isRightChild(node)) {
				node.parent.right = null;

			}
		} else if (numChildren(node) == 1) {

			if (node.left == null) {
				child = node.right;
			} else if (node.right == null) {
				child = node.left;
			}

			if (isLeftChild(node)) {
				node.parent.left = child;
				child.parent = node.parent;
			} else if (isRightChild(node)) {
				node.parent.right = child;
				child.parent = node.parent;
			}

		}

		else if (numChildren(node) == 2) {

			RBNode<E> max = maxLeftSubtree(node);

			node.setData(max.getData());
			delete(max);

		}

	}

	/**
	 * Finds the max node in the left sub tree of a node.
	 * 
	 * @param node
	 *            is the node we are checking.
	 * @return the max node in the left sub tree.
	 */
	public RBNode<E> maxLeftSubtree(RBNode<E> node) {

		RBNode<E> current = node.left;

		if (current != null) {
			while (current.right != null) {
				current = current.right;
			}

			return current;

		}

		return current;
	}

	/**
	 * Finds the sibling of a node.
	 * 
	 * @param node
	 *            is the node that we are trying to find the sibling of.
	 * @return the sibling of a node.
	 */
	RBNode<E> sibling(RBNode<E> node) {

		if (find(node.getData())) {

			if (isRightChild(node) && node.parent.left != null) {
				return node.parent.left;
			}

			else if (isLeftChild(node) && node.parent.right != null) {
				return node.parent.right;
			}
		}
		return null;
	}

	/**
	 * Finds the uncle of a node.
	 * 
	 * @param node
	 *            is the node that we are trying to find the uncle of.
	 * @return the uncle of a node.
	 */
	RBNode<E> uncle(RBNode<E> node) {

		if (node.parent != null) {
			return sibling(node.parent);
		}

		return null;
	}

	/**
	 * Finds the grandparent of a node.
	 * 
	 * @param node
	 *            is the node that we are trying to find the grandparent of.
	 * @return the grandparent of a node.
	 */
	RBNode<E> grandparent(RBNode<E> node) {

		if (node.parent != null && (isLeftChild(node.parent) || isRightChild(node.parent))) {

			return node.parent.parent;

		}

		return null;
	}

	/**
	 * Checks if a node is a left child.
	 * 
	 * @param node
	 *            is the node we are checking.
	 * @return true if the node is a left child, and false if it is not a left
	 *         child.
	 */

	public boolean isLeftChild(RBNode<E> node) {

		if (node.parent == null) {
			return false;
		} else if (node.parent.left == null) {
			return false;
		}

		return node.parent.left.data.equals(node.data);
	}

	/**
	 * Checks if a node is a right child.
	 * 
	 * @param node
	 *            is the node we are checking.
	 * @return true if the node is a right child, and false if it is not a right
	 *         child.
	 */

	public boolean isRightChild(RBNode<E> node) {

		if (node.parent == null) {
			return false;
		}

		else if (node.parent.right == null) {
			return false;
		}

		return node.parent.right.data.equals(node.data);
	}

	/**
	 * A helper method for the pre order traversal method.
	 * 
	 * @return an array list with the correct traversal.
	 */

	public ArrayList<RBNode<E>> preorder() {
		ArrayList<RBNode<E>> myList = new ArrayList<>();

		myList = preorder(root, myList);
		return myList;
	}

	/**
	 * Traverses through the red black tree using the pre order traversal, and puts
	 * values into an array list.
	 * 
	 * @param node
	 *            the node being sent into method.
	 * @param myList
	 *            is the array list used to store nodes.
	 * @return the array list with the nodes in the appropriate order.
	 */

	public ArrayList<RBNode<E>> preorder(RBNode<E> node, ArrayList<RBNode<E>> myList) {

		if (node == null) {
			return myList;
		}

		myList.add(node);

		preorder(node.left, myList);
		preorder(node.right, myList);

		return myList;

	}

	/**
	 * A helper method for the post order traversal method.
	 * 
	 * @return an array list with the correct traversal.
	 */

	public ArrayList<RBNode<E>> postorder() {
		ArrayList<RBNode<E>> myList = new ArrayList<>();

		myList = postorder(root, myList);
		return myList;
	}

	/**
	 * Traverses through the red black tree using the post order traversal, and puts
	 * values into an array list.
	 * 
	 * @param node
	 *            the node being sent into method.
	 * @param myList
	 *            is the array list used to store nodes.
	 * @return the array list with the nodes in the appropriate order.
	 */

	private ArrayList<RBNode<E>> postorder(RBNode<E> node, ArrayList<RBNode<E>> myList) {

		if (node == null) {
			return myList;
		}

		postorder(node.left, myList);
		postorder(node.right, myList);
		// System.out.println(node.getData());

		myList.add(node);

		return myList;
	}

	/**
	 * A helper method for the in order traversal method.
	 * 
	 * @return an array list with the correct traversal.
	 */

	public ArrayList<RBNode<E>> inorder() {
		ArrayList<RBNode<E>> myList = new ArrayList<>();

		myList = inorder(this.root, myList);
		return myList;
	}

	/**
	 * Traverses through the red black tree using the in order traversal, and puts
	 * values into an array list.
	 * 
	 * @param node
	 *            the node being sent into method.
	 * @param myList
	 *            is the array list used to store nodes.
	 * @return the array list with the nodes in the appropriate order.
	 */

	private ArrayList<RBNode<E>> inorder(RBNode<E> node, ArrayList<RBNode<E>> myList) {

		if (node == null) {
			return myList;
		}

		inorder(node.left, myList);

		myList.add(node);
		// System.out.println(node.getData() + " size " + myList.size());

		inorder(node.right, myList);
		;
		return myList;
	}

	/**
	 * A helper method for the breadth first traversal method.
	 * 
	 * @return an array list with the correct traversal.
	 */

	public ArrayList<RBNode<E>> breadthfirst() {

		ArrayList<RBNode<E>> myList = new ArrayList<>();

		myList = breadthfirst(root);
		return myList;
	}

	/**
	 * Traverses through the red black tree using breadth first algorithm. Inputs
	 * into an array list accordingly.
	 * 
	 * @param node
	 *            is the root of the red black tree.
	 * @return an array list of nodes.
	 */

	public ArrayList<RBNode<E>> breadthfirst(RBNode<E> node) {

		ArrayList<RBNode<E>> myList = new ArrayList<>();
		Queue<RBNode<E>> Q = new LinkedList<RBNode<E>>();

		Q.add(root);

		while (!Q.isEmpty()) {
			node = Q.remove();
			myList.add(node);
			System.out.println("data : " + node.getData());

			if (node.left != null) {
				Q.add(node.left);
			}
			if (node.right != null) {
				Q.add(node.right);
			}
		}

		return myList;
	}

	/**
	 * Prints an array list.
	 * 
	 * @param myList
	 *            the list of RBNode that is going to be printed.
	 */

	public void printList(ArrayList<RBNode<E>> myList) {

		for (int i = 0; i < myList.size(); i++) {

			System.out.print(" [ " + myList.get(i).getData() + " ]");
		}

	}

	/**
	 * Implements the red and black tree properties, and ensures the tree be balanced correctly with 
	 * the correct color for each node.
	 * @param node is the node being inserted into the red and black tree.
	 */
	
	private void insertCleanUp(RBNode<E> node) {

		// case 1:
		if (node == root) {
			node.color = "Black";
			return;
		} else {
			node.color = "Red";
		}

		// case 2:

		if (node.parent.color == "Black") {
			return;
		}

		// case 3:

		if (node.parent.color == "Red" && uncle(node) != null) {

			if (uncle(node).color == "Red") {

				node.parent.color = "Black";
				uncle(node).color = "Black";
				grandparent(node).color = "Red";
				insertCleanUp(grandparent(node));
			}
		}

		// case 4:

		if (node.parent.color == "Red" && (uncle(node) == null || uncle(node).color == "Black")) {
			if (isRightChild(node) && isLeftChild(node.parent)) { // case 4ab

				RBNode<E> temp = node;
				RBNode<E> tempParent = node.parent;

				leftRotate(node);

				node = tempParent;
				node.parent = temp;

			}
			if (isLeftChild(node) && isRightChild(node.parent)) {

				RBNode<E> temp = node;
				RBNode<E> tempParent = node.parent;

				rightRotate(node);

				node = tempParent;
				node.parent = temp;

			}
		}

		// case 5 :
		if (node.parent.color == "Red" && (uncle(node) == null || uncle(node).color == "Black")) {

			if (isLeftChild(node) && isLeftChild(node.parent)) {// case 5ab

				grandparent(node).color = "Red";
				node.parent.color = "Black";

				RBNode<E> temp = node;
				rightRotate(node);

				node = node.parent;

			}
			if (isRightChild(node) && isRightChild(node.parent)) {

				grandparent(node).color = "Red";
				node.parent.color = "Black";

				leftRotate(node);
			}
		}

	}

	/**
	 * Left rotates the tree to ensure red and black tree properties are fulfilled. 
	 * @param node is the node used as a pivot.
	 */
	
	private void leftRotate(RBNode<E> node) {

		RBNode<E> temp = node;
		RBNode<E> tempParent = node.parent;
		RBNode<E> tempGrandParent = grandparent(node);

		RBNode<E> tempLeft = tempParent.left;

		if (node.parent.parent == null) {

			root = temp;
			tempParent.right = null;
			root.left = tempParent;

			node.parent = null;
			root.left.parent = node;

		}

		else if (tempGrandParent.parent == null) {

			root = tempParent;
			tempGrandParent.right = null;
			root.left = tempGrandParent;

			node.parent.parent.parent = node.parent;
			node.parent.parent = null;

			if (tempLeft != null) {
				tempLeft.parent = node.parent.left;
				node.parent.left.right = tempLeft;
			}

		}

		else if (isLeftChild(tempParent) && isRightChild(temp)) {

			grandparent(node).left = temp;
			tempParent.right = null;
			node.left = tempParent;

			node.parent = tempGrandParent;
			node.left.parent = temp;

		} else if (isRightChild(tempParent) && isRightChild(temp)) {

			if (isLeftChild(grandparent(node))) {
				grandparent(node).parent.left = node.parent;
			} else {
				grandparent(node).parent.right = node.parent;

			}

			tempGrandParent.right = null;

			node.parent.left = tempGrandParent;

			node.parent.parent = tempGrandParent.parent;
			sibling(node).parent = node.parent;

			if (tempLeft != null) {
				tempLeft.parent = node.parent.left;
				node.parent.left.right = tempLeft;
			}
		}
	}

	/**
	 * Right rotates the tree to ensure red and black tree properties are fulfilled. 
	 * @param node is the node used as a pivot.
	 */
	private void rightRotate(RBNode<E> node) {

		RBNode<E> temp = node;
		RBNode<E> tempParent = node.parent;
		RBNode<E> tempGrandParent = grandparent(node);

		RBNode<E> tempRight = tempParent.right;

		if (node.parent.parent == null) {

			root = temp;
			tempParent.left = null;
			root.right = tempParent;

			node.parent = null;
			root.right.parent = node;

		} else if (tempGrandParent.parent == null) {

			root = tempParent;
			tempGrandParent.left = null;
			root.right = tempGrandParent;

			node.parent.parent.parent = node.parent;

			node.parent.parent = null;

			if (tempRight != null) {
				tempRight.parent = node.parent.right;
				node.parent.right.left = tempRight;
			}
		}

		else if (isRightChild(tempParent) && isLeftChild(temp)) {

			grandparent(node).right = temp;
			tempParent.left = null;
			node.right = tempParent;
			node.parent = tempGrandParent;

			node.right.parent = temp;

		}

		else if (isLeftChild(tempParent) && isLeftChild(temp)) {

			if (isLeftChild(grandparent(node))) {
				grandparent(node).parent.left = node.parent;
			} else {
				grandparent(node).parent.right = node.parent;

			}

			tempGrandParent.left = null;
			node.parent.right = tempGrandParent;

			node.parent.parent = tempGrandParent.parent;
			sibling(node).parent = node.parent;

			if (tempRight != null) {
				tempRight.parent = node.parent.right;
				node.parent.right.left = tempRight;
			}

		}

	}
}