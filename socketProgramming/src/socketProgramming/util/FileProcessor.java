package socketProgramming.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import socketProgramming.util.MyLogger.DebugLevel;


/**
 * The Class FileProcessor performs file related operations.
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class FileProcessor
{
	private BufferedReader br = null;
	
	public FileProcessor(String filePath) throws FileNotFoundException, IOException
	{
		MyLogger.writeMessage("FileProcessor()", DebugLevel.CONSTRUCTOR);
		try
		{
			br  = new BufferedReader(new FileReader(filePath));
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
	}
	
	/**
	 * A thread safe function that reads the file one line
	 * which returns the line back to thecaller function 
	 * @return Returns one line read from the file
	 */
	public synchronized String readLine() throws IOException
	{
		String line = null;
		return ((line = br.readLine()) != null) ?  line.trim() : line;
	}
	

	/**
	 * This is a function is for closing the file
	 * @throws IOException
	 */
	public void closeFile() throws IOException
	{
		try
		{
			
			if(br != null)
				br.close();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return "FileProcessor [bufferedReader=" + br + "]";
	}
}

