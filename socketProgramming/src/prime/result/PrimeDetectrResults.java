package prime.result;

import java.util.Vector;

import prime.socket.DataSender;
import prime.socket.ClientI;
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
public class PrimeDetectrResults implements ResultsI {

	// Stores the handler of PrimeDetectrResults object
	private static ResultsI resultsObj = new PrimeDetectrResults();

	// Stores the prime numbers data
	private Vector<Integer> primeNumsVector;

	// Stores the handler for PrimeDetectorInput object
	private InputParametersI inputParamsObj = PrimeDetectorInput.getInstance();

	// Stores the summation of prime numbers
	private int primeNumsSum;

	// Stores the flag if file read is complete
	private String stopMsg;

	// Stores the thread for DataSender service
	private static Thread dataSenderThread;

	// Stores the boolean value to check is DataSender thread is created or not
	private boolean isDataSndrThrdCreated = false;

	// Stores the DataSender object of type ClientI
	private ClientI dataSenderObj;

	// Stores the result that of prime numbers in a sting format
	private String primeResultStr = "";

	/**
	 * PrimeDetectrResults constructor
	 */
	private PrimeDetectrResults() {

		MyLogger.writeMessage("\nPrimeDetectrResults()", DebugLevel.CONSTRUCTOR);
		stopMsg = "";
		primeNumsVector = new Vector<>();

	}

	/**
	 * This functions returns the single instance of PrimeDetectrResults object
	 * 
	 * @return - The single instance of PrimeDetectrResults object of type ResultsI
	 */
	public static ResultsI getInstance() {
		return resultsObj;
	}

	/**
	 * A thread safe method for adding the prime number read from the file to the
	 * list of prime numbers
	 * 
	 * @param primeNo - The prime number to be added to the list
	 */
	public void addPrimeNum(int primeNum) {
		MyLogger.writeMessage("\naddPrimeNum()", DebugLevel.RESULTS_ADDED);
		synchronized (primeNumsVector) {
			while (primeNumsVector.size() == inputParamsObj.getResultDataCapacity()) {
				try {

					primeNumsVector.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (primeNumsVector.size() < inputParamsObj.getResultDataCapacity()) {

				primeNumsVector.add(primeNum);
				primeNumsSum += primeNum;
				primeResultStr = primeResultStr.concat(primeNum + " ");

				primeNumsVector.notifyAll();
				initDataSndrThread();
			}

		}

	}

	/**
	 * This function returns the data-structure for storing prime numbers
	 * 
	 * @return - The data-structure for storing prime numbers of type
	 *         Vector<Integer>
	 */
	public Vector<Integer> getResultVector() {
		return primeNumsVector;
	}

	/**
	 * This function returns the summation of prime numbers
	 * 
	 * @return - The summation of prime numbers
	 */
	public int getSumOfPrimeNumbers() {

		return primeNumsSum;
	}

	/**
	 * This function that sets a termination flag to "true"
	 */
	public void addTerminationMsg(String message) {
		stopMsg = message;
		dataSenderObj.setFileProcessFlag(true);

	}

	/**
	 * This function returns the size of the result vector for storing prime numbers
	 * 
	 * @return - the size of the result vector for storing prime numbers of type int
	 */
	public int getResultVectorSize() {
		return primeNumsVector.size();
	}

	/**
	 * This function checks if the termination message is set
	 * 
	 * @param message - The message used for termination
	 * @return - True/False if message is set
	 */
	public boolean isTerminationMsgSet(String message) {
		return stopMsg.equals(message);
	}

	/**
	 * This function initialized the DataSender thread and starts the same.
	 */
	public synchronized void initDataSndrThread() {
		dataSenderObj = DataSender.getInstance();
		dataSenderThread = new Thread((Runnable) dataSenderObj);
		if (!isDataSndrThrdCreated) {
			try {
				((Thread) dataSenderThread).start();
				isDataSndrThrdCreated = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "\nResults: " + primeResultStr;
	}

}