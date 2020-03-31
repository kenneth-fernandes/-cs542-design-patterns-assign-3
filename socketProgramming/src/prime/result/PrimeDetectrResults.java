package prime.result;

import java.util.Vector;

import prime.util.InputParametersI;
import prime.util.MyLogger;
import prime.util.PrimeDetectorInput;
import prime.util.MyLogger.DebugLevel;

/**
 * The Class Results store the result of Prime numbers in an ArrayList Data
 * Structure and prints on console
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class PrimeDetectrResults implements PrimeDetectrResultsI {

	// Stores the handler of PrimeDetectrResults object
	private static PrimeDetectrResultsI resultsObj = new PrimeDetectrResults();

	// Stores the prime numbers data
	private Vector<Integer> primeNumsVector;

	// Stores the handler for PrimeDetectorInput object
	private InputParametersI inputParamsObj = PrimeDetectorInput.getInstance();

	// Stores the summation of prime numbers
	private int primeNumsSum;

	/**
	 * PrimeDetectrResults constructor
	 */
	private PrimeDetectrResults() {
		MyLogger.writeMessage("PrimeDetectrResults()", DebugLevel.CONSTRUCTOR);
		primeNumsVector = new Vector<>();
	}

	/**
	 * This functions returns the single instance of PrimeDetectrResults object
	 * 
	 * @return - The single instance of PrimeDetectrResults object of type
	 *         PrimeDetectrResultsI
	 */
	public static PrimeDetectrResultsI getInstance() {
		return resultsObj;
	}

	/**
	 * A thread safe method for adding the prime number read from the file to the
	 * list of prime numbers
	 * 
	 * @param primeNo - The prime number to be added to the list
	 */
	public synchronized void addPrimeNum(int primeNum) {
		MyLogger.writeMessage("addPrimeNum()", DebugLevel.RESULTS_ADDED);

		if (primeNumsVector.size() < inputParamsObj.getResultDataCapacity()) {
			primeNumsVector.add(primeNum);
			primeNumsSum += primeNum;
		}
	}

	/**
	 * This function returns the data-structure for storing prime numbers
	 * 
	 * @return - The data-structure for storing prime numbers of type
	 *         Vector<Integer>
	 */
	public synchronized Vector<Integer> getResultVector() {
		return primeNumsVector;
	}

	/**
	 * This function returns the summation of prime numbers
	 * 
	 * @return - The summation of prime numbers
	 */
	public synchronized int getSumOfPrimeNumbers() {

		return primeNumsSum;
	}

	@Override
	public String toString() {
		return "Results: " + primeNumsVector.toString();
	}
}