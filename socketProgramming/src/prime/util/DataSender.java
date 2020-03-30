package prime.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class DataSender {
    private Socket socket = null;
    private DataOutputStream out = null;

    private InetAddress addrObj = null;
    private int portNum;
    private static DataSender dataSenderObj = new DataSender();

    private DataSender() {
    }

    public static DataSender getInstance(InetAddress addrObj, int portNum)
            throws NumberFormatException, UnknownHostException {
        dataSenderObj.addrObj = addrObj;
        dataSenderObj.portNum = portNum;
        return dataSenderObj;
    }

    public void initSocketConnection() {
        try {
            socket = new Socket(addrObj, portNum);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void processData() {
        Enumeration<Integer> en = Results.getInstance().getResultVector().elements();
        try {
            while (en.hasMoreElements()) {
                out.writeUTF(en.nextElement().toString());
            }
            out.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeConnection() {
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
