package persister.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * PersistToFile
 */
public class PersistToFile implements PersistToFileI {

    private BufferedWriter buffrdWriter;
    private File file;
    private FileWriter fileWriter;
    private static PersistToFileI persistToFileObj = new PersistToFile();

    /**
     * 
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     */
    private PersistToFile() {
        MyLogger.writeMessage("PersistToFile()", DebugLevel.CONSTRUCTOR);

    }

    public static PersistToFileI getInstance() {
        return persistToFileObj;
    }

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
     * 
     * 
     */
    public synchronized void writeLine(String dataStr) throws IOException {
        buffrdWriter.write(dataStr.trim());
        buffrdWriter.flush();
    }

    /**
     *
     * 
     * @throws IOException
     */
    public void closeFile() throws IOException {
        try {
            if (buffrdWriter != null)
                buffrdWriter.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "PersistToFile [buffrdWriter=" + buffrdWriter + "]";
    }
}