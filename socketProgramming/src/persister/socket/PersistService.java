package persister.socket;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

public class PersistService implements PersistServiceI {
    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream inputDataStrmObj = null;
    private BufferedWriter buffrdWriter = null;
    private File file = null;
    private FileWriter fileWriter = null;
    private int portNum;
    private String outputFilePath = "";
    private static PersistServiceI persistSvcObj = new PersistService();

    private PersistService() {
        MyLogger.writeMessage("PersistService()", DebugLevel.CONSTRUCTOR);
    }

    public static PersistServiceI getInstance() {
        return persistSvcObj;
    }

    public void initSocketConnection(int inputDataPortNum, String filePath) throws IOException {

        server = new ServerSocket(inputDataPortNum);
        file = new File(outputFilePath);
        inputDataStrmObj = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void processDataRetrieval() {
        try {
            socket = server.accept();
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            buffrdWriter = new BufferedWriter(fileWriter);
            while (inputDataStrmObj.available() > 0) {
                buffrdWriter.write(inputDataStrmObj.readUTF() + "\n");
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

    public void closeConnection() {
        try {
            socket.close();
            inputDataStrmObj.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    @Override
    public String toString() {

        return "This is PersisterService ( socket = " + socket + " " + server + " " + inputDataStrmObj + " "
                + buffrdWriter + " " + file + " " + fileWriter + " " + portNum + " " + outputFilePath + " "
                + persistSvcObj + " )";
    }
}
