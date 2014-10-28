
public abstract class Node<T> implements Comparable<T>
{
	protected Comparable<T> data;
	protected  Node<T> leftChild;
	protected  Node<T> rightChild;
	protected  Node<T> parent;
	public static final int IS_LEFT = -1;
	public static final int IS_RIGHT = 1;
	public abstract Comparable<T> getNodeValue() throws NullPointerException;
	public abstract boolean isNull();
	public abstract int computeHeight();
	public abstract int leftHeight();
	public abstract int rightHeight();
	public abstract Node<T> left();
	public abstract Node<T> right();
	public abstract Node<T> parent();
}
