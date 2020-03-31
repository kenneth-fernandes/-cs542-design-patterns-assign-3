package prime.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

import prime.result.Results;

public class DataSender implements DataSenderI {
    private Socket socketObj = null;
    private DataOutputStream outDataStreamObj = null;
    private InetAddress addrObj = null;
    private int portNum;
    private static DataSenderI dataSenderObj = new DataSender();
    private Enumeration<Integer> enumeratnObj;

    private DataSender() {
    }

    public static DataSenderI getInstance()
            throws NumberFormatException, UnknownHostException {
        return dataSenderObj;
    }

    public void initSocketConnectn(InetAddress addrObj, int portNum) {
        try {
            socketObj = new Socket(addrObj, portNum);
            outDataStreamObj = new DataOutputStream(socketObj.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processDataTransfer() {
        enumeratnObj = Results.getInstance().getResultVector().elements();
        try {
            while (enumeratnObj.hasMoreElements()) {
                outDataStreamObj.writeUTF(enumeratnObj.nextElement().toString());
            }
            outDataStreamObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnectn() {
        try {
            outDataStreamObj.close();
            socketObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DataSender class data members state: socketObj = " + socketObj+ ", outDataStreamObj =  "
                + outDataStreamObj + ", addrObj = " + addrObj + ", portNum" + portNum + ", dataSenderObj = "
                + dataSenderObj + ", enumeratnObj = " + enumeratnObj;
    }
}
