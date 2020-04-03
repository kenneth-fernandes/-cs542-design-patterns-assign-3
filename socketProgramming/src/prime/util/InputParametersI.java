package prime.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InputParametersI interface - Contains methods for storing and retrieving
 * command line arguments passed to the application
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public interface InputParametersI {

    /**
     * Function to store the Input file path
     * 
     * @param path - File path of type String
     */
    public void setInputFilePath(String path);

    /**
     * Function to retrieve the Input file path
     * 
     * @return - Input filt path of type String
     */
    public String getInputFilePath();

    /**
     * Function to store the count of threads to be used
     * 
     * @param numOfThreadsStr - Count of threads of the type String
     */
    public void setNumOfThreads(String numOfThreadsStr);

    /**
     * Function to retrieve the count of threads
     * 
     * @return - Count of threads of type int
     */
    public int getNumOfThreads();

    /**
     * Function to store the capacity of result data-structure
     * 
     * @param resultDataCapacityStr - Running Average output file path
     */
    public void setResultDataCapacity(String resultDataCapacityStr);

    /**
     * Function to retrieve the capacity of result data-structure
     * 
     * @return- capacity of result data-structure of type int
     */
    public int getResultDataCapacity();

    /**
     * Function to set the IP Address of the Persister Service
     * 
     * @param path - IP Address of type String
     * @throws UnknownHostException
     */
    public void setPersistSvcIPAddr(String ipAddrStr) throws UnknownHostException;

    /**
     * Function to set the IP Address of the Persister Service
     * 
     * @return - IP Address of return type InetAddress
     */
    public InetAddress getPersistSvcIPAddr();

    /**
     * Function to store the port number of the Persister Service
     * 
     * @param portNumStr - Port number of type String
     */
    public void setPersistSvcPortNum(String portNumStr);

    /**
     * Function to retrieve the port number of the Persister Service
     * 
     * @return - Port number of type int
     */
    public int getPersistSvcPortNum();

    /**
     * Function to store the value used for debug
     * 
     * @param debugValStr - debug value of type String
     */
    public void setDebugValue(String debugValStr);

    /**
     * Function to retrieve the value used for debug
     * 
     * @return - debug value of type int
     */
    public int getDebugValue();

}