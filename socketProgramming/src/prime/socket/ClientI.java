package prime.socket;

/**
 * ClientI interface
 */
public interface ClientI {

   /**
   * This function processes sending the data to the Persister Service server
   * 
   */
  public void processDataTransfer();

  /**
   * This function closes the socket connection of the DataSender client
   */
  public void closeConnectn();

  /**
   * This function sets the flag of file processing completion variable
   * @param flag - The flag to be set of type boolean
   */
  public void setFileProcessFlag(boolean flag);
}