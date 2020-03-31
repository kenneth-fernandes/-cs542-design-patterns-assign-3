package persister.socket;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import persister.result.PersisterResults;
import persister.result.PersisterResultsI;
import persister.util.PersistToFile;
import persister.util.PersistToFileI;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * 
 */
public class PersistService implements PersistServiceI {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream inputDataStrmObj;
    private BufferedWriter buffrdWriter;
    private File file;
    private FileWriter fileWriter;
    private int portNum;
    private String outputFilePath;
    private String resultDataStr;
    private static PersistServiceI persistSvcObj = new PersistService();
    private PersisterResultsI persistrResultsObj;
    private PersistToFileI persistToFileObj;

    /**
     * 
     */
    private PersistService() {
        MyLogger.writeMessage("PersistService()", DebugLevel.CONSTRUCTOR);
        resultDataStr = "";
        outputFilePath = "";
        persistrResultsObj = PersisterResults.getInstance();
        persistToFileObj = PersistToFile.getInstance();
    }

    /**
     * 
     * @return
     */
    public static PersistServiceI getInstance() {
        return persistSvcObj;
    }

    /**
     * 
     */
    public void initSocketConnection(int inputDataPortNum, String filePath) throws IOException {

        server = new ServerSocket(inputDataPortNum);
        outputFilePath = filePath;
        inputDataStrmObj = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * 
     */
    public void processDataRetrieval() {
        try {
            socket = server.accept();

            while (resultDataStr.equals("STOP") || inputDataStrmObj.available() > 0) {
                resultDataStr = inputDataStrmObj.readUTF();
                persistrResultsObj.storeResultData(resultDataStr);
            }

            persistToFileObj.openFile(outputFilePath);
            persistToFileObj.writeLine(persistrResultsObj.getStoredPersisterResult());
            persistToFileObj.closeFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public void closeConnection() {
        try {
            socket.close();
            inputDataStrmObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {

        return "This is PersisterService ( socket = " + socket + " " + server + " " + inputDataStrmObj + " "
                + buffrdWriter + " " + file + " " + fileWriter + " " + portNum + " " + outputFilePath + " "
                + persistSvcObj + " )";
    }
}
