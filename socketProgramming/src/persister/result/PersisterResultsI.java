package persister.result;

/**
 * PersisterResultsI
 */
public interface PersisterResultsI {

    public void storeResultData(String result);

    public String getStoredPersisterResult();

    public int getStoredResultAvg();

}