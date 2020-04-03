package persister.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilePersisterI {
    /**
     * This function open the file and intializes BufferedWriter
     * 
     * @param filePath - Path of the file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void openFile(String filePath) throws FileNotFoundException, IOException;

    /**
     * This function writes data to bufferedWrite
     * 
     * @param dataStr - The data of type String
     */
    public void writeLine(String dataStr) throws IOException;

    /**
     * This function closes the file connection and flushes the buffrdWriter to the
     * file
     * 
     * @throws IOException
     */
    public void closeFile() throws IOException;

}
