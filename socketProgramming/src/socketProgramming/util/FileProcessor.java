package socketProgramming.src.socketProgramming.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import socketProgramming.src.socketProgramming.util.MyLogger.DebugLevel;


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
	
	
	public synchronized String readLine() throws IOException
	{
		String line = null;
		return ((line = br.readLine()) != null) ?  line.trim() : line;
	}
	
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