package persister.result;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * PersisterResults
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PersisterResults implements PersisterResultsI {

    private String persisterResult = "";

    private static PersisterResultsI persistResultObj = new PersisterResults();

    /**
     * 
     */
    private PersisterResults() {
        MyLogger.writeMessage("PersisterResults()", DebugLevel.CONSTRUCTOR);
    }

    /**
     * 
     * @return
     */
    public static PersisterResultsI getInstance() {
        return persistResultObj;
    }

    /**
     * 
     */
    public void storeResultData(String result) {
        persisterResult = persisterResult.concat(result + "\n");
    }

    /**
     * 
     * @return
     */
    public String getStoredResult() {
        return persisterResult;
    }

}