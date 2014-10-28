package flyweight;

import java.awt.Font;

public class Run 
{
	private int start;
	private int end;
	private Font font;
	
	public Run(int start,int end, Font font)
	{
		this.start = start;
		this.end = end;
		this.font = font;
	}
	
	public boolean isIndexInRange(int index)
	{
		if(start<=index && end<=index)
			return true;
		else
			return false;
	}
	
	public Font getFont()
	{
		return font;
	}
}
