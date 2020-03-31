package prime.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import prime.result.PrimeDetectrResults;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * This class contains methods for implemnting Client Socket by sending data to
 * the Persister ServiceServer
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class DataSender implements DataSenderI {

    // Stores the Socket handler of the DataSender client
    private Socket socketObj;

    // Stores the DataOutputStream handler
    private DataOutputStream outDataStreamObj;

    // Stores the InetAddress handler that stores IP Address of the Persister
    // Service Server
    private InetAddress addrObj;

    // Stores the port number
    private int portNum;

    // Stores the handler of DataSender client
    private static DataSenderI dataSenderObj = new DataSender();

    // Stores the Enumeration object handler of type Integer
    private Enumeration<Integer> enumeratnObj;

    /**
     * DataSender Constructor
     */
    private DataSender() {
        MyLogger.writeMessage("DataSender()", DebugLevel.CONSTRUCTOR);
    }

    /**
     * This function returns the single instance of DataSender
     * 
     * @return - Instance of DataSender class
     * @throws NumberFormatException
     * @throws UnknownHostException
     */
    public static DataSenderI getInstance() throws NumberFormatException, UnknownHostException {
        return dataSenderObj;
    }

    /**
     * This function intilializes the socket connection
     * 
     * @param addrObj - InetAddress object handler
     * @param portNum - Port number of type String
     */
    public void initSocketConnectn(InetAddress addrObj, int portNum) {
        try {
            socketObj = new Socket(addrObj, portNum);
            outDataStreamObj = new DataOutputStream(socketObj.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function processes sending the data to the Persister Service server
     * 
     */
    public void processDataTransfer() {
        enumeratnObj = PrimeDetectrResults.getInstance().getResultVector().elements();
        try {
            while (enumeratnObj.hasMoreElements()) {
                outDataStreamObj.writeUTF(enumeratnObj.nextElement().toString());
            }
            outDataStreamObj.writeUTF("STOP");
            outDataStreamObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function closes the socket connection of the DataSender client
     */
    public void closeConnectn() {
        try {
            outDataStreamObj.close();
            socketObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DataSender class :() socketObj = " + socketObj + ", outDataStreamObj =  " + outDataStreamObj
                + ", addrObj = " + addrObj + ", portNum" + portNum + ", dataSenderObj = " + dataSenderObj
                + ", enumeratnObj = " + enumeratnObj + ")";
    }
}
