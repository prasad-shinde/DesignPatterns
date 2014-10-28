package videoStore;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public class CommandProcessor 
{
	private ArrayList<Command> commands;
	private static CommandProcessor instance=null;
	
	protected CommandProcessor()
	{
		commands = new ArrayList<Command>();
	}
	
	public static CommandProcessor getInstance()
	{
		if(instance == null)
			instance = new CommandProcessor();
		return instance;
	}
	
	public void add(Command command)
	{
		commands.add(command);
	}
	
	public void removeAll()
	{
		instance = null;
	}
	
	public void runCommands() throws InvocationTargetException, IllegalAccessException
	{
		Command command;
		Iterator<Command> iterator = commands.iterator();
		
		while(iterator.hasNext())
		{
			command = iterator.next();
			command.execute();
		}
	}
}
