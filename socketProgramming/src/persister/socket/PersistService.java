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

    /**
     * 
     */
    private PersistService() {
        MyLogger.writeMessage("PersistService()", DebugLevel.CONSTRUCTOR);
        resultDataStr = "";
        outputFilePath = "";
        persistrResultsObj = PersisterResults.getInstance();
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
        file = new File(outputFilePath);
        inputDataStrmObj = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * 
     */
    public void processDataRetrieval() {
        try {
            socket = server.accept();
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            buffrdWriter = new BufferedWriter(fileWriter);

            while (resultDataStr.equals("STOP") || inputDataStrmObj.available() > 0) {
                resultDataStr = inputDataStrmObj.readUTF();
                persistrResultsObj.storeResultData(resultDataStr);

                buffrdWriter.write(resultDataStr + "\n");
                buffrdWriter.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffrdWriter != null)
                    buffrdWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
