package prime.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;

import prime.result.PrimeDetectrResults;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * This class contains methods for implemnting Client Socket by sending data to
 * the Persister ServiceServer
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class DataSender implements Runnable {

    // Stores the Socket handler of the DataSender client
    private Socket socketObj;

    // Stores the DataOutputStream handler
    private DataOutputStream outDataStreamObj;

    // Stores the InetAddress handler that stores IP Address of the Persister
    // Service Server
    private InetAddress addrObj;

    // Stores the port number
    private int portNum;

    // Stores the capacity
    private int capacity;

    // Stores the handler of DataSender client
    // private static DataSenderI dataSenderObj = new DataSender();

    // Stores the Enumeration object handler of type Integer
    private Enumeration<Integer> enumeratnObj;

    // Stores the results of prime numbers
    private Vector<Integer> primeNumsVector;

    // Stores the status of data sending
    private boolean isFileProcessCompleted = false;

    /**
     * DataSender Constructor
     */
    public DataSender(InetAddress addrObj, int portNum, int capacity) {
        MyLogger.writeMessage("DataSender()", DebugLevel.CONSTRUCTOR);
        initSocketConnectn(addrObj, portNum, capacity);
    }

    /**
     * This function returns the single instance of DataSender
     * 
     * @return - Instance of DataSender class
     * @throws NumberFormatException
     * @throws UnknownHostException
     */
    /*
     * public static DataSenderI getInstance() throws NumberFormatException,
     * UnknownHostException { return dataSenderObj; }
     */

    /**
     * This function intilializes the socket connection
     * 
     * @param addrObj - InetAddress object handler
     * @param portNum - Port number of type String
     */
    private synchronized void initSocketConnectn(InetAddress addrObj, int portNum, int capacity) {
        try {
            socketObj = new Socket(addrObj, portNum);
            outDataStreamObj = new DataOutputStream(socketObj.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        processDataTransfer();
        if (isFileProcessCompleted) {
            closeConnectn();
        }
    }

    /**
     * This function processes sending the data to the Persister Service server
     * 
     */
    public synchronized void processDataTransfer() {
        primeNumsVector = PrimeDetectrResults.getInstance().getResultVector();
        System.out.println("processDataTransfer() - DataSender. - Notify()");

        while (primeNumsVector.size() == 0) {
            try {
                System.out.println("processDataTransfer() - DataSender. - wait()");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        try {
            while (primeNumsVector.size() != 0) {
                outDataStreamObj.writeUTF(primeNumsVector.remove(0).toString());
            }
            outDataStreamObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function closes the socket connection of the DataSender client
     */
    public synchronized void closeConnectn() {
        try {
            System.out.println("Close connectn - Data Sender");
            outDataStreamObj.close();
            socketObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setFileProcessFlag(boolean flag) {
        isFileProcessCompleted = flag;
    }

    @Override
    public String toString() {
        return "DataSender class :() socketObj = " + socketObj + ", outDataStreamObj =  " + outDataStreamObj
                + ", addrObj = " + addrObj + ", portNum" + portNum + ", enumeratnObj = " + enumeratnObj + ")";
    }

}
