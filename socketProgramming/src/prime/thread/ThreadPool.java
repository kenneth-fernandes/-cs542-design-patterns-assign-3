package prime.thread;

import java.util.ArrayList;
import java.util.List;

import prime.calculation.IsPrimeI;
import prime.result.ResultsI;
import prime.util.FileProcessorI;

/**
 * ThreadPool is a class for implementing the thread pool
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class ThreadPool {

	// Stores the list of Threads of type Runnable
	private static List<Runnable> threadList;

	// Store the count of threads
	private static int threadCount = 0;

	/**
	 * Function to add the thread to the Thread pool
	 * 
	 * @param The Thread object "t" is to be added to Thread Pool
	 */
	public static void addThread(Thread t) {
		threadList.add(t);
	}

	/**
	 * Function for creating the threads for the thread pool
	 * 
	 * @param The numOfThreads is a paramter with value of number of thread in a
	 *            thread pool
	 * @param The FileProcessor object "fileProcessorObj" for performing file operations
	 * @param The Results object "primeDetectrResultsObj" for storing the prime numbers
	 * @param The IsPrime object "isPrimeObj" for checking if a number is prime
	 */
	public static void createThreads(int numOfThreads, FileProcessorI fileProcessorObj,
			ResultsI primeDetectrResultsObj, IsPrimeI isPrimeObj) {
		threadCount = numOfThreads;
		threadList = new ArrayList<Runnable>();

		for (int i = 0; i < numOfThreads; i++) {
			addThread(new Thread(new WorkerThread(fileProcessorObj, isPrimeObj, primeDetectrResultsObj)));
		}
	}

	/**
	 * This is a function to borrow the thread from the Thread pool
	 */
	public static Runnable borrowThread() {
		if (threadList.size() != 0)
			return threadList.remove(0);
		return null;
	}

	/**
	 * This is a function to add the returned object back to Thread pool
	 */
	public void returnThread(Thread t) {
		threadList.add(t);
	}

	/**
	 * This is a function for getting the number of active threads.
	 * 
	 * @return Returns the number of active threads
	 */
	public int activeThreads() {
		return (threadCount - threadList.size());
	}

	/**
	 * This is a Function for getting the number of idle threads in the pool.
	 * 
	 * @return Returns the idle number of threads
	 */
	public int idleThreads() {
		return threadList.size();
	}


	/**
	 * This is a Function for the list of threads.
	 * 
	 * @return Returns the the list of threads of type List<Runnable>.
	 */
	public static List<Runnable> getThreadLst(){
		return threadList;
	}

	@Override
	public String toString() {
		return "Size of Thread pool is " + threadCount;
	}
}
