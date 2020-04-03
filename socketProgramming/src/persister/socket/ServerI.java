package persister.socket;

import java.io.IOException;

/**
 * ServerI
 */
public interface ServerI {

    /**
     * This functio intialized the PersistService Server Socket connection
     * 
     * @param inputDataPortNum - Port number
     * @param filePath         - File path to persist data to output file
     * @exception IOException
     */
    public void initSocketConnection(int inputDataPortNum, String filePath) throws IOException;

    /**
     * This function processes the Data retrieval from the Data Sender Client
     */
    public void processDataRetrieval();

    /**
     * This function Closes the Server Socket connectiom
     */
    public void closeConnection();

}