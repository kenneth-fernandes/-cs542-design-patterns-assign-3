package persister.result;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * This class contains methods that stores the results of the prime detector
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PersisterResults implements PersisterResultsI {

    // Stores the prime number result in the format to persist to theoutput file
    private String persisterResult;

    // Stores the instance of PersisterResults of type PersisterResultsI
    private static PersisterResultsI persistResultObj = new PersisterResults();

    /**
     * PersisterResults constructor
     */
    private PersisterResults() {
        MyLogger.writeMessage("\nPersisterResults()", DebugLevel.CONSTRUCTOR);
        persisterResult = "";
    }

    /**
     * This function returns the single instance object of PersisterResults of type
     * PersisterResultsI
     * 
     * @return - instance object of PersisterResults of type PersisterResultsI
     */
    public static PersisterResultsI getInstance() {
        return persistResultObj;
    }

    /**
     * This function stores the data sent from Data Sender client
     * 
     * @param result - The data sent by the Data Sender client
     */
    public void storeResultData(String result) {
        persisterResult = persisterResult.concat(result + "\n");
    }

    /**
     * This function returns the stored results that would be peristed to the output
     * file of type String
     * 
     * @return - the stored results to be persisted of type String
     */
    public String getStoredPersisterResult() {
        return persisterResult;
    }

    @Override
    public String toString() {
        return "PersisterResults : " + persisterResult;
    }
}