

public interface HeapOrderingStrategy<T>
{
	public abstract void add(Node<T> node,Object element);
	public abstract Node<T> createHeap(Object[] list);
}
