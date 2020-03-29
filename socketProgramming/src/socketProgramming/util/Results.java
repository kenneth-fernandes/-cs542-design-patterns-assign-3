package socketProgramming.src.socketProgramming.util;

import java.util.ArrayList;
import java.util.List;
import socketProgramming.src.socketProgramming.util.MyLogger.DebugLevel;

/**
 * This class implements the StdoutDisplayInterface
 *  and writes the sum of the Prime numbers to the
 *  console 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Results 
{
	private List<Integer> primeNumList;
	
	public Results()
	{
		MyLogger.writeMessage("Results()", DebugLevel.CONSTRUCTOR);
		primeNumList = new ArrayList<Integer>();
	}
	
	/**
	 * A thread safe method for adding the prime number read from
	 *  the file to the list of prime numbers
	 * @param primeNo The prime number to be added to the list
	 */
	public synchronized void addPrimeNum(int primeNum)
	{
		MyLogger.writeMessage("addPrimeNo()", DebugLevel.RESULTS_ADDED);
        primeNumList.add(primeNum);
        for (int i =0;i < primeNumList.size(); i++){
            primeNumList.get(i);
        
        }
	}

	@Override
	public String toString()
	{
		return "Results: " + primeNumList.toString();
	}
}