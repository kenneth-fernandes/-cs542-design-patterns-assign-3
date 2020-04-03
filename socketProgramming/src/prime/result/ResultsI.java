package prime.result;

import java.util.Vector;

/**
 * ResultsI interface
 */
public interface ResultsI {

    /**
     * A thread safe method for adding the prime number read from the file to the
     * list of prime numbers
     * 
     * @param primeNo - The prime number to be added to the list
     */
    public void addPrimeNum(int primeNum);

    /**
     * This function returns the data-structure for storing prime numbers
     * 
     * @return - The data-structure for storing prime numbers of type
     *         Vector<Integer>
     */
    public Vector<Integer> getResultVector();

    /**
     * This function returns the summation of prime numbers
     * 
     * @return - The summation of prime numbers
     */
    public int getSumOfPrimeNumbers();

    /**
     * This function stores the termination message to the variable
     * 
     * @param message
     */
    public void addTerminationMsg(String message);

    /**
     * This function returns the size of the result vector for storing prime numbers
     * 
     * @return - the size of the result vector for storing prime numbers of type int
     */
    public int getResultVectorSize();

    /**
     * This function checks if the termination message is set
     * 
     * @param message - The message used for termination
     * @return - True/False if message is set
     */
    public boolean isTerminationMsgSet(String message);

    /**
     * This function initialized the DataSender thread and starts the same.
     */
    public void initDataSndrThread();
}