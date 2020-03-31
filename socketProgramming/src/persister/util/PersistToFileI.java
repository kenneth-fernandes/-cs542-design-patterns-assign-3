package persister.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersistToFileI {
    public void openFile(String filePath) throws FileNotFoundException, IOException;

    public void writeLine(String dataStr) throws IOException;

    public void closeFile() throws IOException;

}
