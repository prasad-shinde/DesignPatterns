
public class MinHeapStrategy<T> implements HeapOrderingStrategy<T>
{

	//adds value to min heap
	@Override
	public void add(Node<T> node, Object value) 
	{
		if(node.isNull())
		{
			((NullNode<T>)node).add(value);
			return;
		}
		// >= so that heap turns to a Max Heap
		if(node.compareTo((T) value) >= 0)
		{
			value = ((BinaryTreeNode<T>)node).swapNodeValueWith(value);
		}
		if(node.rightHeight() < node.leftHeight())
		{
			add(node.right(),value);
		}
		else
		{
			add(node.left(),value);
		}
	}
	
	// creates a min heap of the elements of list
	@Override
	public Node<T> createHeap(Object[] list) 
	{
		Node<T> root = null;
		root = new BinaryTreeNode<T>(list[0]);
		for(int i=1;i<list.length;i++)
			add(root,list[i]);
		return root;
	}
}
