package prime.util;

import java.io.IOException;

/**
 * FileProcessorI interface
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public interface FileProcessorI {

    /**
     * A thread safe function that reads the file one line which returns the line
     * back to thecaller function
     * 
     * @return Returns one line read from the file
     */
    public String readLine() throws IOException;


	/**
	 * This is a function is for closing the file
	 * 
	 * @throws IOException
	 */
	public void closeFile() throws IOException;

}