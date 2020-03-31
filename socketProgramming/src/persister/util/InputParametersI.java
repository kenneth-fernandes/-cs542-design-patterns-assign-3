package persister.util;

/**
 * InputParametersI interface
 */
public interface InputParametersI {

    /**
     * Function to store Output file path
     * 
     * @param path - File path
     */
    public void setOutputFilePath(String path);

    /**
     * Function to retrieve Output file path
     * 
     * @return - Output file path of type String
     */
    public String getOutputFilePath();

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

}