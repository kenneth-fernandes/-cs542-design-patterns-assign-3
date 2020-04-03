package persister.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import persister.result.PersisterResults;
import persister.result.PersisterResultsI;
import persister.util.PrimesDataFilePersister;
import persister.util.FilePersisterI;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * This class contains methods to initiate Persister Service and listen to any
 * incoming request that needs to be process
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PersistService implements ServerI {

    // Stores the Persist Service socket
    private Socket socket;

    // Stores the Persist Service server socket
    private ServerSocket server;

    // Stores the DataInputStream object for reading the data sent by Data Sender
    private DataInputStream inputDataStrmObj;

    // Stores the port number for server to listen
    private int portNum;

    // Store the file path of output
    private String outputFilePath;

    // Stores the result data sent by DataSender
    private String resultDataStr;

    // Stores the instance of PersistService of type ServerI
    private static ServerI persistSvcObj = new PersistService();

    // Stores the instance of PersisterResults of type PersisterResultsI
    private PersisterResultsI persistrResultsObj;

    // Stores the instance of PersistToFile of type PersistToFileI
    private FilePersisterI persistToFileObj;

    /**
     * PersistService constructor
     */
    private PersistService() {
        MyLogger.writeMessage("\n\nPersistService()", DebugLevel.CONSTRUCTOR);
        resultDataStr = "";
        outputFilePath = "";
        persistrResultsObj = PersisterResults.getInstance();
        persistToFileObj = PrimesDataFilePersister.getInstance();
    }

    /**
     * Yhis function returns the single instance of PersistService of type ServerI
     * 
     * @return - Instance of PersistService of type ServerI
     */
    public static ServerI getInstance() {
        return persistSvcObj;
    }

    /**
     * This functio intialized the PersistService Server Socket connection
     * 
     * @param inputDataPortNum - Port number
     * @param filePath         - File path to persist data to output file
     * @exception IOException
     */
    public void initSocketConnection(int inputDataPortNum, String filePath) throws IOException {

        server = new ServerSocket(inputDataPortNum);
        System.out.println("\nPersister Service Started....");
        System.out.println("\nWaiting for client to connect....");
        socket = server.accept();
        inputDataStrmObj = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputFilePath = filePath;

    }

    /**
     * This function processes the Data retrieval from the Data Sender Client
     */
    public void processDataRetrieval() {
        try {
            while (true) {
                if (inputDataStrmObj.available() > 0) {
                    resultDataStr = inputDataStrmObj.readUTF();
                    if (resultDataStr.equals("STOP")) {
                        break;
                    } else {
                        System.out.println("\nPersister Service: Data Received from Client : " + resultDataStr);
                    }
                    persistrResultsObj.storeResultData(resultDataStr);
                }
            }
            persistToFileObj.openFile(outputFilePath);
            persistToFileObj.writeLine(persistrResultsObj.getStoredPersisterResult());
            persistToFileObj.closeFile();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function Closes the Server Socket connectiom
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

        return "This is PersisterService ( socket = " + socket + ", server =  " + server + ", inputDataStrmObj = "
                + inputDataStrmObj + ", portNum =  " + portNum + ", outputFilePath =  " + outputFilePath
                + ", persistSvcObj = " + persistSvcObj + " )";
    }
}
