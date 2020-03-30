package socketProgramming.util;

import java.util.Vector;

import socketProgramming.util.MyLogger.DebugLevel;

/**
 * The Class Results store the result of Prime numbers in an ArrayList Data
 * Structure and prints on console
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Results {
	private Vector<Integer> primeNumsVector;
	private InputParametersData inputParamsDataObj = InputParametersData.getInstance();

	public Results() {
		MyLogger.writeMessage("Results()", DebugLevel.CONSTRUCTOR);
		primeNumsVector = new Vector<>();
	}

	/**
	 * A thread safe method for adding the prime number read from the file to the
	 * list of prime numbers
	 * 
	 * @param primeNo The prime number to be added to the list
	 */
	public synchronized void addPrimeNum(int primeNum) {
		MyLogger.writeMessage("addPrimeNo()", DebugLevel.RESULTS_ADDED);
		if (primeNumsVector.size() < inputParamsDataObj.getResultDataCapacity()) {
			primeNumsVector.add(primeNum);
		}
	}

	@Override
	public String toString() {
		return "Results: " + primeNumsVector.toString();
	}
}