package videoStore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Command implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Object receiver;
	private Method command;
	private Object[] arguments;
	
	public Command(Object receiver,Method command,Object[] arguments)
	{
		this.receiver = receiver;
		this.command = command;
		this.arguments = arguments;
	}
	
	public void setReceiver(Object receiver)
	{
		this.receiver = receiver;
	}
	
	public Object execute() throws InvocationTargetException,IllegalAccessException
	{
		// invokes the command on the receiver with given arguments to the method
		return command.invoke(receiver, arguments);
	}
	
	private void writeObject(final ObjectOutputStream out) throws IOException
	{
	      out.writeObject(this.arguments);
	      out.writeObject(this.command.getDeclaringClass());	// Method class is not serializable
	      out.writeObject(this.command.getName());				// need to serialize required information
	      out.writeObject(this.command.getParameterTypes());
	}
	
	private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		Class<?> clsName;
		String methodName;  
		Object params;
		this.arguments= (Object[]) in.readObject();
	    clsName = (Class<?>) in.readObject();
	    methodName = (String)in.readObject();
	    params =  in.readObject();
	    try 
	    {
	    	this.command = clsName.getMethod(methodName, (Class<?>[]) params);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
