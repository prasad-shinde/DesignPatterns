package flyweight;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

public class RunArray 
{
	private ArrayList<Run> runs;
	private int end;
	
	public RunArray()
	{
		runs = new ArrayList<Run>();
		end=0;
	}
	
	public boolean addRun(int start,int end,Font font)
	{
		runs.add(new Run(start,end,font));
		this.end=end;
		return true;
	}
	
	public boolean appendRun(int width,Font font)
	{
		runs.add(new Run(this.end+1,this.end+width,font));
		this.end+=width;
		return true;
	}
	
	// returns the state if present else returns null
	public Font getState(int index)
	{
		Run currentRun;
		Iterator<Run> i = runs.iterator();
		while(i.hasNext())
		{
			currentRun = i.next();
			if(currentRun.isIndexInRange(index))
			{
				return currentRun.getFont();
			}
		}
		return null;
	}
}
