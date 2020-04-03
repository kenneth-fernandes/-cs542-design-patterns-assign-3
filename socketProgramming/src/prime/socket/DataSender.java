package prime.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;

import prime.util.InputParametersI;
import prime.result.PrimeDetectrResults;
import prime.util.MyLogger;
import prime.util.PrimeDetectorInput;
import prime.util.MyLogger.DebugLevel;

/**
 * This class contains methods for implemnting Client Socket by sending data to
 * the Persister ServiceServer
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class DataSender implements Runnable, ClientI {

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
    private static ClientI dataSenderObj = new DataSender();

    // Stores the Enumeration object handler of type Integer
    private Enumeration<Integer> enumeratnObj;

    // Stores the results of prime numbers
    private Vector<Integer> primeNumsVector;

    // Stores the status of data sending
    private boolean isFileProcessCompleted;

    private InputParametersI inputParamObj = PrimeDetectorInput.getInstance();

    /**
     * DataSender Constructor
     */
    private DataSender() {
        MyLogger.writeMessage("\nDataSender()", DebugLevel.CONSTRUCTOR);

        initSocketConnectn(inputParamObj.getPersistSvcIPAddr(), inputParamObj.getPersistSvcPortNum());

        isFileProcessCompleted = false;
    }

    /**
     * This function returns the single instance of DataSender
     * 
     * @return - Instance of DataSender class
     * @throws NumberFormatException
     * @throws UnknownHostException
     */

    public static ClientI getInstance() {
        return dataSenderObj;
    }

    /**
     * This function intilializes the socket connection
     * 
     * @param addrObj - InetAddress object handler
     * @param portNum - Port number of type String
     */
    private void initSocketConnectn(InetAddress addrObj, int portNum) {
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
                try {

                    while (primeNumsVector.size() == 0) {
                        try {
                            primeNumsVector.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    primeNumsVector.notifyAll();
                    while (primeNumsVector.size() != 0) {

                        String data = primeNumsVector.remove(0).toString();
                        outDataStreamObj.writeUTF(data);
                        System.out.println("\nDataSender Service: Data Sent to the server - " + data);
                        outDataStreamObj.flush();
                    }
                    if (isFileProcessCompleted) {
                        outDataStreamObj.writeUTF("STOP");
                        outDataStreamObj.flush();
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
            outDataStreamObj.close();
            socketObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function sets the flag of file processing completion variable
     * 
     * @param flag - The flag to be set of type boolean
     */
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
