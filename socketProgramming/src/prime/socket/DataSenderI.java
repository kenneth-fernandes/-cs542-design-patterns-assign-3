package prime.socket;

import java.net.InetAddress;

/**
 * DataSenderI interface
 */
public interface DataSenderI {

    /**
     * This function intilializes the socket connection
     * 
     * @param addrObj - InetAddress object handler
     * @param portNum - Port number of type String
     */
   // public void initSocketConnectn(InetAddress addrObj, int portNum, int capacity);

    /**
     * This function processes sending the data to the Persister Service server
     * 
     */
    public void processDataTransfer();

    /**
     * This function closes the socket connection of the DataSender client
     */
    public void closeConnectn();
}