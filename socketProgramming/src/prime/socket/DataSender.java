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
    private void initSocketConnectn(InetAddress addrObj, int portNum, int capacity) {
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
        closeConnectn();
    }

    /**
     * This function processes sending the data to the Persister Service server
     * 
     */
    public void processDataTransfer() {
        primeNumsVector = PrimeDetectrResults.getInstance().getResultVector();
        synchronized (primeNumsVector) {
            while (true) {
                System.out.println("While true processDataTransfer()");
                try {

                    while (primeNumsVector.size() == 0) {
                        try {
                            System.out.print("\nData Sender - processDataTransfer() - wait().");
                            primeNumsVector.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    primeNumsVector.notifyAll();
                    while (primeNumsVector.size() != 0) {
                        System.out.println(Thread.currentThread().getName() + " Thread - Element - "
                                + primeNumsVector.get(0).toString());
                        String s = primeNumsVector.remove(0).toString();
                        outDataStreamObj.writeUTF(s);
                        outDataStreamObj.flush();
                    }
                    if (isFileProcessCompleted) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

    public void setFileProcessFlag(boolean flag) {
        if (!isFileProcessCompleted)
            isFileProcessCompleted = flag;
    }

    @Override
    public String toString() {
        return "DataSender class :() socketObj = " + socketObj + ", outDataStreamObj =  " + outDataStreamObj
                + ", addrObj = " + addrObj + ", portNum" + portNum + ", enumeratnObj = " + enumeratnObj + ")";
    }

}
