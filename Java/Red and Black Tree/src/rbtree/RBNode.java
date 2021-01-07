package rbtree;

public class RBNode<E extends Comparable<E>> {

	protected RBNode<E> parent;
	protected RBNode<E> left;
	protected RBNode<E> right;
	
	protected String color;//B is black, DB is double black, R is red.
	
	protected E data;
	
	/**
	 * Creates an RBNode.
	 * @param data is the data that is stored in the given node.
	 */
	
	public RBNode(E data) {
		this.data = data;
	}
	
	/**
	 * A setter for the node.
	 * @param data is the value that will be set into the node.
	 */
	public void setData(E data) {
		this.data=data;
	}
	/**
	 * A getter for the value inside the node.
	 * @return the data in the node.
	 */
	public E getData() {
		return this.data;
	}	
}
