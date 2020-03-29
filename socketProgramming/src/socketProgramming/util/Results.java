package socketProgramming.util;

import java.util.ArrayList;
import java.util.List;
import socketProgramming.util.MyLogger.DebugLevel;

/**
 * The Class Results store the result of Prime numbers in an ArrayList 
 * Data Structure and prints on console
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Results {
	private List<Integer> primeNumList;

	public Results() {
		MyLogger.writeMessage("Results()", DebugLevel.CONSTRUCTOR);
		primeNumList = new ArrayList<Integer>();
	}

	/**
	 * A thread safe method for adding the prime number read from the file to the
	 * list of prime numbers
	 * 
	 * @param primeNo The prime number to be added to the list
	 */
	public synchronized void addPrimeNum(int primeNum) {
		MyLogger.writeMessage("addPrimeNo()", DebugLevel.RESULTS_ADDED);
		primeNumList.add(primeNum);
	}

	@Override
	public String toString() {
		System.out.println(primeNumList.toString());
		return "Results: " + primeNumList.toString();
	}
}

