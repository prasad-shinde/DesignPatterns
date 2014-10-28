
public class NullNode<T> extends Node<T>
{
	private int whichChild;
	
	public NullNode()
	{
		parent = null;
	}
	
	public NullNode(Node<T> node,int child)
	{
		parent = node;
		whichChild = child;
	}
	
	public int compareTo(T value)
	{
		throw new NullPointerException();
	}
	
	// replaces itself and adds a BinaryTreeNode to the parent
	public void add(Object value)
	{
		if(whichChild == IS_LEFT)
		{
			((BinaryTreeNode<T>)parent).addLeft(value);
		}
		else
		{
			((BinaryTreeNode<T>)parent).addRight(value);
		}
	}
	
	public String toString()
	{
			return "";
	}
	
	
	public int computeHeight()
	{
		return 0;
	}
	
	public int leftHeight()
	{
		return 0;
	}
	
	public int rightHeight()
	{
		return 0;
	}
	
	
	public boolean isNull()
	{
		return true;
	}
	
	@Override
	public Comparable<T> getNodeValue()
	{
		throw new NullPointerException();
	}
	
	@Override
	public Node<T> left() 
	{
		return parent;
	}
	
	@Override
	public Node<T> right() 
	{
		return parent;
	}
	
	@Override
	public Node<T> parent() 
	{
		return parent;
	}

}
