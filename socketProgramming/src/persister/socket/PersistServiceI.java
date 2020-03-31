package persister.socket;

import java.io.IOException;

/**
 * PersistServiceI
 */
public interface PersistServiceI {

    public void initSocketConnection(int inputDataPortNum, String filePath) throws IOException;

    public void processDataRetrieval();

    public void closeConnection();

}