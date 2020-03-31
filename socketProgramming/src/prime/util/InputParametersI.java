package prime.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InputParametersI
 */
public interface InputParametersI {

    public void setInputFilePath(String path);

    public String getInputFilePath();

    public void setNumOfThreads(String numOfThreadsStr);

    public int getNumOfThreads();

    public void setResultDataCapacity(String resultDataCapacityStr);

    public int getResultDataCapacity();

    public void setPersistSvcIPAddr(String ipAddrStr) throws UnknownHostException;

    public InetAddress getPersistSvcIPAddr();

    public void setPersistSvcPortNum(String portNumStr);

    public int getPersistSvcPortNum();

    public void setDebugValue(String debugValStr);

    public int getDebugValue();

}