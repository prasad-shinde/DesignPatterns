package videoStore;

import java.util.AbstractCollection;
import java.util.NoSuchElementException;

public abstract class Inventory extends AbstractCollection
{
	public abstract boolean sell(Integer id,Integer copies);
	
	public abstract boolean sell(String name,Integer copies);
	
	public abstract boolean addCopy(Integer id,Integer copies);
	
	public abstract boolean addCopy(String name,Integer copies);

	public abstract boolean changePrice(Integer id,Integer price);
	
	public abstract boolean changePrice(String name,Integer price);
	
	public abstract int findPrice(int id) throws NoSuchElementException;
	
	public abstract int findPrice(String name) throws NoSuchElementException;
	
	public abstract int findQuantity(int id)throws NoSuchElementException;
	
	public abstract int findQuantity(String name)throws NoSuchElementException;
	
	public abstract Object createMemento();
	
	public abstract void restoreMemento(Object oldSnapshot);
}
