
public class BinaryTreeNode<T> extends Node<T>
{	
	public BinaryTreeNode()
	{
		data = null;
		parent = null;
		leftChild = new NullNode<T>(this,IS_LEFT);
		rightChild = new NullNode<T>(this,IS_RIGHT);
	}
	
	public BinaryTreeNode(Object value)
	{
		data = (Comparable<T>)value;
		this.parent = null;
		leftChild = new NullNode<T>(this,IS_LEFT);
		rightChild = new NullNode<T>(this,IS_RIGHT);
	}
	
	public void addLeft(Object value)
	{
		leftChild = new BinaryTreeNode<T>(value);
		((BinaryTreeNode<T>)leftChild).setParent(this);
	}
	
	public void addRight(Object value)
	{
		rightChild = new BinaryTreeNode<T>(value);
		((BinaryTreeNode<T>)rightChild).setParent(this);
	}
	
	// compares this object with the specified value
	public int compareTo(T value)
	{
		return data.compareTo(value);
	}
	
	private void setParent(Node<T> parent)
	{
		this.parent = parent;
	}
	
	public boolean isNull()
	{
		return false;
	}
	
	public Node<T> parent()
	{
		return parent;
	}
	
	// recursive function which actually returns the preorder output
	public String toString()
	{
			return this.getNodeValue() + ","+ this.left().toString() + this.right().toString();
	}
	
	public int computeHeight()
	{
		return(1+Math.max(this.left().computeHeight(), this.right().computeHeight()));
	}
	
	public int leftHeight()
	{
		return leftChild.computeHeight();
	}
	
	public int rightHeight()
	{
		return rightChild.computeHeight();
	}
	
	// Swaps the node value with the argument and returns the original value
	public Comparable<T> swapNodeValueWith(Object value)
	{
		Comparable<T> originalValue;
		originalValue = data;
		data = (Comparable<T>) value;
		return originalValue;
	}
	
	public Comparable<T> getNodeValue()
	{
		return data;
	}
	
	public Node<T> left()
	{
		return leftChild;
	}
	
	public Node<T> right()
	{
		return rightChild;
	}
}