package prime.result;

import java.util.Vector;

/**
 * PrimeDetectrResultsI interface
 */
public interface PrimeDetectrResultsI {

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
}