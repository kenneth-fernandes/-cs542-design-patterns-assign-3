package persister.util;

/**
 * Class that contains methods to set and retireve the argument passed through
 * command line
 */
public class PersistSvcInput implements InputParametersI {
    /**
     * Data members of PersistSvcInput containing input arguments and
     * PersistSvcInput object
     */
    private static InputParametersI inputParamsObj = new PersistSvcInput();

    private int persistSvcPort;

    private String outputFilePath;

    /**
     * InputParametersData constructor
     * 
     * @throws Exception
     */
    private PersistSvcInput() {
    }

    /**
     * Function that returns the InputParametersData singleton object
     * 
     * @return - Returns the InputParametersData object
     */
    public static InputParametersI getInstance() {

        return inputParamsObj;
    }

    /**
     * Function to store Output file path
     * 
     * @param path - File path
     */
    public void setOutputFilePath(String path) {
        outputFilePath = path;
    }

    /**
     * Function to retrieve Output file path
     * 
     * @return - Output file path of type String
     */
    public String getOutputFilePath() {
        return outputFilePath;
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

    @Override
    public String toString() {
        return "InputParameters class data members : " + outputFilePath + " " + persistSvcPort;
    }
}