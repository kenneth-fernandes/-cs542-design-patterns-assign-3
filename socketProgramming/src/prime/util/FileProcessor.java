package prime.util;

import prime.util.MyLogger.DebugLevel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class FileProcessor performs file related operations.
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class FileProcessor implements FileProcessorI {

	// The BufferedReader for reading data from the file
	private BufferedReader br;

	/**
	 * The FileProcessor constructor that initializes Buffered Reader with filepath
	 * 
	 * @param filePath - Path of the file to be read
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FileProcessor(String filePath) throws FileNotFoundException, IOException {
		MyLogger.writeMessage("\nFileProcessor()", DebugLevel.CONSTRUCTOR);
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	/**
	 * A thread safe function that reads the file one line which returns the line
	 * back to thecaller function
	 * 
	 * @return Returns one line read from the file
	 */
	public synchronized String readLine() throws IOException {
		String line = null;
		return ((line = br.readLine()) != null) ? line.trim() : line;
	}

	/**
	 * This is a function is for closing the file
	 * 
	 * @throws IOException
	 */
	public synchronized void closeFile() throws IOException {
		try {

			if (br != null)
				br.close();
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public String toString() {
		return "FileProcessor [bufferedReader=" + br + "]";
	}
}
