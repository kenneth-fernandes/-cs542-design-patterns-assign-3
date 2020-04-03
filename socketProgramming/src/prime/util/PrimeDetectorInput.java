package prime.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import prime.util.MyLogger.DebugLevel;

/**
 * Class that contains methods to set and retireve the arguments passed through
 * command line
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PrimeDetectorInput implements InputParametersI {
    /**
     * Data members of PrimeDetectorInput class
     */

    // Stores handler of PrimeDetectorInput object
    private static InputParametersI inputParamsObj = new PrimeDetectorInput();

    // Stores file path of input file
    private String inputFilePath;

    // Stores the count of threads to be used by the application
    private int numOfThreads;

    // Stores the capacity for storing prime result data
    private int resultDataCapacity;

    // Stores the IP Address of the Persister Service
    private InetAddress persistSvcIPAddrObj;

    // Stores the port number of the Persister Service
    private int persistSvcPort;

    // Stores the value used for debug
    private int debugVal;

    /**
     * PrimeDetectorInput constructor
     */
    private PrimeDetectorInput() {
        MyLogger.writeMessage("\nPrimeDetectorInput()", DebugLevel.CONSTRUCTOR);
    }

    /**
     * Function that returns the single instance of PrimeDetectorInput object
     * 
     * @return - Returns the PrimeDetectorInput object
     */
    public static InputParametersI getInstance() {

        return inputParamsObj;
    }

    /**
     * Function to store the Input file path
     * 
     * @param path
     */
    public void setInputFilePath(String path) {
        inputFilePath = path;
    }

    /**
     * Function to retrieve the Input file path
     * 
     * @return - Input filt path of type String
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Function to store the count of threads to be used
     * 
     * @param numOfThreadsStr - Count of threads of the type String
     */
    public void setNumOfThreads(String numOfThreadsStr) {
        numOfThreads = Integer.parseInt(numOfThreadsStr);
    }

    /**
     * Function to retrieve the count of threads
     * 
     * @return - Count of threads of type int
     */
    public int getNumOfThreads() {
        return numOfThreads;
    }

    /**
     * Function to store the capacity of result data-structure
     * 
     * @param resultDataCapacityStr - Running Average output file path
     */
    public void setResultDataCapacity(String resultDataCapacityStr) {
        resultDataCapacity = Integer.parseInt(resultDataCapacityStr);
    }

    /**
     * Function to retrieve the capacity of result data-structure
     * 
     * @return- capacity of result data-structure of type int
     */
    public int getResultDataCapacity() {
        return resultDataCapacity;
    }

    /**
     * Function to set the IP Address of the Persister Service
     * 
     * @param path - IP Address of type String
     * @throws UnknownHostException
     */
    public void setPersistSvcIPAddr(String ipAddrStr) throws UnknownHostException {
        persistSvcIPAddrObj = InetAddress.getByName(ipAddrStr);
    }

    /**
     * Function to set the IP Address of the Persister Service
     * 
     * @return - IP Address of return type InetAddress
     */
    public InetAddress getPersistSvcIPAddr() {
        return persistSvcIPAddrObj;
    }

    /**
     * Function to store the port number of the Persister Service
     * 
     * @param portNumStr - Port number of type String
     */
    public void setPersistSvcPortNum(String portNumStr) {
        persistSvcPort = Integer.parseInt(portNumStr);
    }

    /**
     * Function to retrieve the port number of the Persister Service
     * 
     * @return - Port number of type int
     */
    public int getPersistSvcPortNum() {
        return persistSvcPort;
    }

    /**
     * Function to store the value used for debug
     * 
     * @param debugValStr - debug value of type String
     */
    public void setDebugValue(String debugValStr) {
        debugVal = Integer.parseInt(debugValStr);
    }

    /**
     * Function to retrieve the value used for debug
     * 
     * @return - debug value of type int
     */
    public int getDebugValue() {
        return debugVal;
    }

    @Override
    public String toString() {
        return "InputParameters class : ( inputFilePath = " + inputFilePath + ", numOfThreads = " + numOfThreads
                + ", resultDataCapacity  " + resultDataCapacity + ", persistSvcIPAddrObj.getAddress() = "
                + persistSvcIPAddrObj.getAddress() + ", persistSvcPort = " + persistSvcPort + ", debugVal = " + debugVal
                + " )";
    }
}