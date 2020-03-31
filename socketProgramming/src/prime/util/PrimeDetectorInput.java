package prime.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class that contains methods to set and retireve the argument passed through
 * command line
 */
public class PrimeDetectorInput implements InputParametersI {
    /**
     * Data members of InputParametersData containing input arguments and
     * InputParametersData object
     */
    private static InputParametersI inputParamsObj = new PrimeDetectorInput();

    private String inputFilePath;

    private int numOfThreads;

    private int resultDataCapacity;

    private InetAddress persistSvcIPAddrObj;

    private int persistSvcPort;

    private int debugVal;

    /**
     * InputParametersData constructor
     * 
     * @throws Exception
     */
    private PrimeDetectorInput() {
    }

    /**
     * Function that returns the InputParametersI singleton object
     * 
     * @return - Returns the InputParametersI object
     */
    public static InputParametersI getInstance() {

        return inputParamsObj;
    }

    /**
     * Function to set Input file path
     * 
     * @param path
     */
    public void setInputFilePath(String path) {
        inputFilePath = path;
    }

    /**
     * Function to get Input file path
     * 
     * @return - Input filt path
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Function to set Number of threads
     * 
     * @param path - Number peaks output file path
     */
    public void setNumOfThreads(String numOfThreadsStr) {
        numOfThreads = Integer.parseInt(numOfThreadsStr);
    }

    /**
     * Function to get Number peaks output file path
     * 
     * @return - Number peaks output file path
     */
    public int getNumOfThreads() {
        return numOfThreads;
    }

    /**
     * Function to set Running Average output file path
     * 
     * @param path - Running Average output file path
     */
    public void setResultDataCapacity(String resultDataCapacityStr) {
        resultDataCapacity = Integer.parseInt(resultDataCapacityStr);
    }

    /**
     * Function to set Running Average output file path
     * 
     * @return- Running Average output file path
     */
    public int getResultDataCapacity() {
        return resultDataCapacity;
    }

    /**
     * Function to set Top K Numbers Output file path
     * 
     * @param path - Top K Numbers Output file path
     * @throws UnknownHostException
     */
    public void setPersistSvcIPAddr(String ipAddrStr) throws UnknownHostException {
        persistSvcIPAddrObj = InetAddress.getByName(ipAddrStr);
    }

    /**
     * Function to get Top K Numbers Output file path
     * 
     * @return - Top K Numbers Output file path
     */
    public InetAddress getPersistSvcIPAddr() {
        return persistSvcIPAddrObj;
    }

    /**
     * Function to set Running Average window size
     * 
     * @param windowSizeStr - Running Average window size
     * @throws NumberFormatException - Parsing to integer error exception
     */
    public void setPersistSvcPortNum(String portNumStr) {
        persistSvcPort = Integer.parseInt(portNumStr);
    }

    /**
     * Function to get Running Average window size
     * 
     * @return - Running Average window size
     */
    public int getPersistSvcPortNum() {
        return persistSvcPort;
    }

    /**
     * Function to set the K value for Top K Numbers
     * 
     * @param kValueStr - K value for Top K Numbers of type string
     * @throws NumberFormatException - Parsing to integer error exception
     */
    public void setDebugValue(String debugValStr) {
        debugVal = Integer.parseInt(debugValStr);
    }

    /**
     * Function to get the K value for Top K Numbers
     * 
     * @return - K value for Top K Numbers
     */
    public int getDebugValue() {
        return debugVal;
    }

    @Override
    public String toString() {
        return "InputParameters class data members : " + inputFilePath + " " + numOfThreads + " " + resultDataCapacity
                + " " + persistSvcIPAddrObj.toString() + " " + persistSvcPort + " " + debugVal;
    }
}