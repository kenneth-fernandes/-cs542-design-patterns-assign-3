package persisterservice.util;

/**
 * Class that contains methods to set and retireve the argument passed through
 * command line
 */
public class InputParametersData {
    /**
     * Data members of InputParametersData containing input arguments and
     * InputParametersData object
     */
    private static InputParametersData inputParamsDataObj = new InputParametersData();

    private int persistSvcPort;

    private String outputFilePath;

    /**
     * InputParametersData constructor
     * 
     * @throws Exception
     */
    private InputParametersData() {
    }

    /**
     * Function that returns the InputParametersData singleton object
     * 
     * @return - Returns the InputParametersData object
     */
    public static InputParametersData getInstance() {

        return inputParamsDataObj;
    }

    /**
     * Function to set Input file path
     * 
     * @param path
     */
    public void setInputFilePath(String path) {
        outputFilePath = path;
    }

    /**
     * Function to get Input file path
     * 
     * @return - Input filt path
     */
    public String getOutputFilePath() {
        return outputFilePath;
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

    @Override
    public String toString() {
        return "InputParameters class data members : " + outputFilePath + " " + persistSvcPort;
    }
}