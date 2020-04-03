package prime.thread;

import java.io.IOException;

import prime.calculation.IsPrimeI;
import prime.result.ResultsI;
import prime.util.FileProcessorI;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * The CreateWorkers Class contains startWorkers() method which borrow
 * NUM_THREADS threads
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class CreateWorkers {
	// Stores the handler of CreateWorkers instance
	private static CreateWorkers createWrkrsObj = new CreateWorkers();

	// Stores the handler of FileProcessor
	private FileProcessorI fileProcessorObj;

	// Stores the handler of IsPrime instance
	private IsPrimeI isPrimeObj;

	// Stores the handler of PrimeDetectrResults instance
	private ResultsI primeDetectrResultsObj;

	private CreateWorkers() {
		MyLogger.writeMessage("\nCreateWorkers()", DebugLevel.CONSTRUCTOR);
	}

	/**
	 * The function returns the single object of CreateWorkers class
	 * 
	 * @param fileProcessorObjIn       - The FileProcessor class object
	 * @param resultsIn   - The Results class object
	 * @param isPrimeObjIn - The IsPrime class object
	 * @return - The CreateWorkers class object
	 */
	public static CreateWorkers getInstance(FileProcessorI fileProcessorObjIn, ResultsI resultsIn, IsPrimeI isPrimeObjIn) {
		createWrkrsObj.fileProcessorObj = fileProcessorObjIn;
		createWrkrsObj.isPrimeObj = isPrimeObjIn;
		createWrkrsObj.primeDetectrResultsObj = resultsIn;
		return createWrkrsObj;
	}

	/**
	 * The method borrows NUM_THREADS threads i.e start them and join on them. The
	 * instances of FileProcessor, Results, and IsPrime should be passed to he
	 * constructor of the worker thread class.
	 * 
	 * @param numOfThreads The number of threads passed as command line argument to
	 *                     the program
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void startWorkers(int numOfThreads) throws InterruptedException, IOException {
		/**
		 * To Call the createThreads function with required set of parameters
		 */
		ThreadPool.createThreads(numOfThreads, fileProcessorObj, primeDetectrResultsObj, isPrimeObj);

		/*
		 * Borrowing of the threads, one thread at a time and starting the thread and
		 * also performing join on the thread
		 */
		for (int i = 0; i < numOfThreads; i++) {
			Runnable runnable = ThreadPool.borrowThread();
			if (runnable != null) {
				((Thread) runnable).start();
				try {
					((Thread) runnable).join();
				} catch (InterruptedException e) {
					throw e;
				}
			}
		}

		// Closing the file in file processor
		try {
			fileProcessorObj.closeFile();
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public String toString() {
		return "CreateWorkers (createWrkrsObj = " + createWrkrsObj + ", fileProcessorObj = " + fileProcessorObj
				+ ", primeDetectrResultsObj = " + primeDetectrResultsObj + ", isPrimeObj =" + isPrimeObj + ")";
	}
}