package persister.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * PersistToFile class contains methods to write the data to the output file
 */
public class PrimesDataFilePersister implements FilePersisterI {

    // Stores the BufferedWriter instance
    private BufferedWriter buffrdWriter;

    // Stores the File instance
    private File file;

    // Stores the FileWriter instance
    private FileWriter fileWriter;

    // Stores the PersistToFile instance of type PersistToFileI
    private static FilePersisterI persistToFileObj = new PrimesDataFilePersister();

    /**
     * PersistToFile constructor
     */
    private PrimesDataFilePersister() {
        MyLogger.writeMessage("\nPersistToFile()", DebugLevel.CONSTRUCTOR);

    }

    /**
     * This function returns the PersistToFile instance of type PersistToFileI
     */
    public static FilePersisterI getInstance() {
        return persistToFileObj;
    }

    /**
     * This function open the file and intializes BufferedWriter
     * 
     * @param filePath - Path of the file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public synchronized void openFile(String filePath) throws FileNotFoundException, IOException {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            buffrdWriter = new BufferedWriter(fileWriter);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * This function writes data to bufferedWrite
     * 
     * @param dataStr - The data of type String
     */
    public synchronized void writeLine(String dataStr) throws IOException {
        buffrdWriter.write(dataStr.trim());
    }

    /**
     * This function closes the file connection and flushes the buffrdWriter to the
     * file
     * 
     * @throws IOException
     */
    public void closeFile() throws IOException {
        try {
            buffrdWriter.flush();
            if (buffrdWriter != null)
                buffrdWriter.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "PersistToFile : (buffrdWriter=" + buffrdWriter + ", file = " + file + ", fileWriter ="
                + persistToFileObj + ")";
    }
}