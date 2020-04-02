package persister.result;

/**
 * PersisterResultsI
 */
public interface PersisterResultsI {

    /**
     * This function stores the data sent from Data Sender client
     * 
     * @param result - The data sent by the Data Sender client
     */
    public void storeResultData(String result) throws InterruptedException;

    /**
     * This function returns the stored results that would be peristed to the output
     * file of type String
     * 
     * @return - the stored results to be persisted of type String
     */
    public String getStoredPersisterResult();
}