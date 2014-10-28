package videoStore;

import java.io.Serializable;

public class Movie implements Cloneable,Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private int price;
	private int uniqueId;
	private int quantity;
	
	public Movie(String name,int price,int quantity)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.uniqueId = MovieIdGenerator.getInstance().getId();
	}
	
	public int getId()
	{
		return uniqueId;
	}
	
	public String movieName()
	{
		return name;
	}
	
	public int quantity()
	{
		return quantity;
	}
	
	public void changePrice(int price)
	{
		this.price = price;
	}
	
	public int price()
	{
		return this.price;
	}
	
	protected Object clone()
	{
		Movie copy = new Movie(this.name,this.price,this.quantity);
		copy.uniqueId = this.uniqueId;
		return copy;
	}
	
	public void decreaseQuantity(int quantity) throws Exception
	{
		int finalCopies;
		if(this.quantity == 0)
		{
			throw new Exception("No more copies");
		}
		else
		{
			finalCopies = this.quantity - quantity;
			if(finalCopies<0)
				throw new Exception("Insufficient number of copies");
			this.quantity = finalCopies;
		}
	}
	
	public void increaseQuantity(int quantity)
	{
		this.quantity = quantity + this.quantity;
	}
}
