package persister.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
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
 * This class contains methods to initiate Persister Service and listen to any
 * incoming request that needs to be process
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PersistService implements PersistServiceI {

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

    // Stores the instance of PersistService of type PersistServiceI
    private static PersistServiceI persistSvcObj = new PersistService();

    // Stores the instance of PersisterResults of type PersisterResultsI
    private PersisterResultsI persistrResultsObj;

    // Stores the instance of PersistToFile of type PersistToFileI
    private PersistToFileI persistToFileObj;

    /**
     * PersistService constructor
     */
    private PersistService() {
        MyLogger.writeMessage("PersistService()", DebugLevel.CONSTRUCTOR);
        resultDataStr = "";
        outputFilePath = "";
        persistrResultsObj = PersisterResults.getInstance();
        persistToFileObj = PersistToFile.getInstance();
    }

    /**
     * Yhis function returns the single instance of PersistService of type
     * PersistServiceI
     * 
     * @return - Instance of PersistService of type PersistServiceI
     */
    public static PersistServiceI getInstance() {
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
        System.out.println("Persister Service Started....");
        System.out.println("Waiting for PrimeDetector client to connect....");
        socket = server.accept();

        inputDataStrmObj = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputFilePath = filePath;

    }

    /**
     * This function processes the Data retrieval from the Data Sender Client
     */
    public void processDataRetrieval() {
        try {

            while (!resultDataStr.equals("STOP")) {
                if (inputDataStrmObj.available() > 0) {
                    resultDataStr = inputDataStrmObj.readUTF();
                    persistrResultsObj.storeResultData(resultDataStr);
                    System.out.println(resultDataStr);
                }
            }

            persistToFileObj.openFile(outputFilePath);
            persistToFileObj.writeLine(persistrResultsObj.getStoredPersisterResult());
            persistToFileObj.closeFile();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
