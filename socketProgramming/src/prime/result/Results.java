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
public class Results {
	private static Results resultsObj = new Results();
	private Vector<Integer> primeNumsVector;
	private InputParametersI inputParamsObj = PrimeDetectorInput.getInstance();

	private Results() {
		MyLogger.writeMessage("Results()", DebugLevel.CONSTRUCTOR);
		primeNumsVector = new Vector<>();
	}

	public static Results getInstance(){
		return resultsObj;
	}

	/**
	 * A thread safe method for adding the prime number read from the file to the
	 * list of prime numbers
	 * 
	 * @param primeNo The prime number to be added to the list
	 */
	public synchronized void addPrimeNum(int primeNum) {
		MyLogger.writeMessage("addPrimeNo()", DebugLevel.RESULTS_ADDED);
		
		if (primeNumsVector.size() < inputParamsObj.getResultDataCapacity()) {
			primeNumsVector.add(primeNum);
		}
	}

	public synchronized Vector<Integer> getResultVector() {
		return primeNumsVector;
	}

	@Override
	public String toString() {
		return "Results: " + primeNumsVector.toString();
	}
}