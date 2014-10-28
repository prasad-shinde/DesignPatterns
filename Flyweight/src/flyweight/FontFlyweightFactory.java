package flyweight;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FontFlyweightFactory 
{
	private List<Font> pool;
	private static FontFlyweightFactory instance = null;
	
	private FontFlyweightFactory()
	{
		pool = new ArrayList<Font>();
	}

	public static FontFlyweightFactory getInstance()
	{
		if(instance == null)
		{
			instance = new FontFlyweightFactory();
		}
		return instance;
	}
	
	// checks for the given font in the pool, if not creates a new one and returns it.
	public Font getFont(String fontName,int pointSize,int style)
	{
		Font currentFont;
		Iterator<Font> i = pool.iterator();
		while(i.hasNext())
		{
			currentFont = i.next();
			if(currentFont.getName().equals(fontName) && (currentFont.getSize()==pointSize) && (currentFont.getStyle() == style))
			{
				return currentFont;
			}
		}
		
		currentFont = new Font(fontName,pointSize,style);
		pool.add(currentFont);
		return currentFont;
	}

}
