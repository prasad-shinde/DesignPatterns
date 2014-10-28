package videoStore;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class InventoryDecorator extends Inventory
{
	private Inventory inventory;
	private String commandFile = "command.ser";
	private String tempInventoryFile = "tempInventory.ser";
	private String inventoryMementoFile = "inventory.ser";
	
	public InventoryDecorator()
	{
		inventory = new VideoStoreInventory();
	}
	
	public InventoryDecorator(VideoStoreInventory inventory)
	{
		this.inventory = inventory;
	}
	
	public boolean add(Object element)
	{
		Class[] argumentTypes = {Object.class};
		Method add;
		try {
				add = VideoStoreInventory.class.getMethod("add", argumentTypes);
				Object[] arguments = {element};
				return this.saveAndExecuteCommand(argumentTypes, arguments, add);
			} catch (NoSuchMethodException | SecurityException e) {
				return false;
		}
	}
	
	public boolean sell(Integer id,Integer copies)
	{
		Class[] argumentTypes = {Integer.class,Integer.class};
		Object[] arguments = {id,copies};
		Method sellMovies;
		try {
			sellMovies = VideoStoreInventory.class.getMethod("sell",argumentTypes);
			return this.saveAndExecuteCommand(argumentTypes, arguments, sellMovies);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public boolean sell(String name,Integer copies)
	{
		Class[] argumentTypes = {String.class,Integer.class};
		Object[] arguments = {name,copies};
		Method sellMovies;
		try {
			sellMovies = VideoStoreInventory.class.getMethod("sell",argumentTypes);
			return this.saveAndExecuteCommand(argumentTypes, arguments, sellMovies);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	private boolean saveAndExecuteCommand(Class[] argumentTypes,Object[] arguments,Method method)
	{
		Command command = new Command(inventory,method,arguments);
		try {
			command.execute();
			this.saveCommand(command);
			return true;
		} catch (InvocationTargetException | IllegalAccessException e) {
			return false;
		}
	}
	
	public boolean addCopy(Integer id,Integer copies)
	{
		Class[] argumentTypes = {Integer.class,Integer.class};
		Method addCopy;
		try {
			addCopy = VideoStoreInventory.class.getMethod("addCopy", argumentTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
		Object[] arguments = {id,copies};
		return this.saveAndExecuteCommand(argumentTypes, arguments, addCopy);
	}
	
	public boolean addCopy(String name,Integer copies)
	{
		Class[] argumentTypes = {String.class,Integer.class};
		Method addCopy;
		try {
			addCopy = VideoStoreInventory.class.getMethod("addCopy", argumentTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
		Object[] arguments = {name,copies};
		return this.saveAndExecuteCommand(argumentTypes, arguments, addCopy);
	}
	
	public boolean changePrice(String name,Integer price)
	{
		Class[] argumentTypes = {String.class,Integer.class};
		Method changePrice;
		try {
			changePrice = VideoStoreInventory.class.getMethod("changePrice", argumentTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
		Object[] arguments = {name,price};
		return this.saveAndExecuteCommand(argumentTypes, arguments, changePrice);
	}

	public boolean changePrice(Integer id,Integer price)
	{
		Class[] argumentTypes = {Integer.class,Integer.class};
		Method changePrice;
		try {
			changePrice = VideoStoreInventory.class.getMethod("changePrice", argumentTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
		Object[] arguments = {id,price};
		return this.saveAndExecuteCommand(argumentTypes, arguments, changePrice);
	}
	
	private void saveCommand(Command currentCommand)
	{
		FileOutputStream fileOut;
		try 
		{
			fileOut = new FileOutputStream(commandFile,true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(currentCommand);
			out.close();
			fileOut.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int findPrice(int id)
	{
		return inventory.findPrice(id);
	}
	
	public int findPrice(String name)
	{
		return inventory.findPrice(name);
	}
	
	public int findQuantity(int id)
	{
		return inventory.findQuantity(id);
	}
	
	public int findQuantity(String name)
	{
		return inventory.findQuantity(name);
	}
	
	public Object createMemento()
	{
		Object memento = inventory.createMemento();
		saveMemento(memento);
		return memento;
	}
	
	private void purgeOldCommands()
	{
		File file = new File(this.commandFile);
		file.mkdirs();
		
		try 
		{
			if(file.exists())
			{
				file.delete();
				file.createNewFile();
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void saveMemento(Object memento)
	{
		FileOutputStream fileOut;
				
		try 
		{
			fileOut = new FileOutputStream(this.tempInventoryFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(memento);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			// File writing failed
			File temporaryFile = new File(this.tempInventoryFile);
		    if(temporaryFile.exists())
		    {
		    	temporaryFile.delete();
		    }
			return;
		}		
		
		// renaming files
		File temporaryFile = new File(this.tempInventoryFile);

	    // File (or directory) with new name
	    File originalFile = new File(this.inventoryMementoFile);
	    if(originalFile.exists())
	    {
	    	originalFile.delete();
	    }

	    // Rename file (or directory)
	    boolean success = temporaryFile.renameTo(originalFile);
	    if (!success) {
	    	return;
	    }
		
		this.purgeOldCommands();
	}
	
	private void restoreCheckpoint()
	{
		Command command;
		CommandProcessor processor = CommandProcessor.getInstance();
		try
	    {
	         FileInputStream fileIn = new FileInputStream(this.commandFile);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         // read all commands from file
	         while((command= (Command)in.readObject())!=null)
	         {
	        	 command.setReceiver(inventory);
	        	 processor.add(command);
	        	 try
	        	 {
	        		 in = new ObjectInputStream(fileIn); 
	        	 }
	        	 catch(EOFException e)
	        	 {
	        		 break;
	        	 }
	         }
	         in.close();
	         fileIn.close();
	         
	         // execute all commands
	         processor.runCommands();
	         processor.removeAll();
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void restoreMemento(Object oldSnapshot) {
		this.inventory.restoreMemento(oldSnapshot);
	}
	
	public void restoreMemento()
	{
		Object oldSnapshot;
		try
	    {
	         FileInputStream fileIn = new FileInputStream(this.inventoryMementoFile);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         oldSnapshot = in.readObject();
	         in.close();
	         fileIn.close();
	         if(oldSnapshot!=null)
	        	 inventory.restoreMemento(oldSnapshot);
	         else
	        	 inventory = new VideoStoreInventory();
	         this.restoreCheckpoint();
	         this.createMemento();
	    }
		catch(IOException | ClassNotFoundException e)
	    {
	         e.printStackTrace();
	    }
	}
		
	public Iterator iterator() 
	{
		return inventory.iterator();
	}

	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
