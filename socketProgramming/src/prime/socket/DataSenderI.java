package prime.socket;

import java.net.InetAddress;

/**
 * DataSenderI
 */
public interface DataSenderI {
    public void initSocketConnectn(InetAddress addrObj, int portNum);

    public void processDataTransfer();

    public void closeConnectn();
}