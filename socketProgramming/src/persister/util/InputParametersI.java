package persister.util;

/**
 * InputParametersI
 */
public interface InputParametersI {

    public void setOutputFilePath(String path);

    public String getOutputFilePath();

    public void setPersistSvcPortNum(String portNumStr);

    public int getPersistSvcPortNum();

}