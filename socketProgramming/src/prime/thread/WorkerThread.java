package prime.thread;

import java.io.IOException;

import prime.calculation.IsPrimeI;
import prime.result.ResultsI;
import prime.util.FileProcessorI;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * WorkerThread class is for implementing the worker thread which reads the
 * line, checks if the number is prime and appends the number to the results
 * class.
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class WorkerThread implements Runnable {

	// Stores the handler of File Procssor object
	private FileProcessorI fileProcessorObj;

	// Stores the handler of IsPrime class
	private IsPrimeI isPrimeObj;

	// Stores the handler of PrimeDetectrResults class
	private ResultsI primeDetectrResultsObj;

	/**
	 * WorkerThread constructor
	 * 
	 * @param fileProcessorInObj - FileProcessor Object
	 * @param isPrimeInObj - IsPrime instance of type IsPrimeI
	 * @param primeDetectrResultsInObj - PrimeDetectrResults instance of type ResultsI
	 */
	public WorkerThread(FileProcessorI fileProcessorInObj, IsPrimeI isPrimeInObj,
			ResultsI primeDetectrResultsInObj) {
		MyLogger.writeMessage("\nWorkerThread()", DebugLevel.CONSTRUCTOR);
		this.fileProcessorObj = fileProcessorInObj;
		this.isPrimeObj = isPrimeInObj;
		this.primeDetectrResultsObj = primeDetectrResultsInObj;
	}

	/**
	 * This is a function which Invoke a method in the FileProcessor to retrieve
	 * one line, as string from the input file and Check if it is a prime
	 * number. Also stores the prime number in a data structure in the Results
	 * instance
	 */
	@Override
	public void run() {
		MyLogger.writeMessage("\nrun()", DebugLevel.THREAD_RUN);
		try {
			String line = null;
			/*
			 * This reads the file line by line until the end of the file is
			 * reached
			 */
			synchronized (this) {
				while ((line = fileProcessorObj.readLine()) != null) {
					// Check if the number is prime
					boolean prime = false;

					prime = isPrimeObj.checkNum(Integer.parseInt(line));
					if (prime) {
						primeDetectrResultsObj.addPrimeNum(Integer.parseInt(line));
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		primeDetectrResultsObj.addTerminationMsg("STOP");
	}

	@Override
	public String toString() {
		return "This is a WorkerThread (fileProcessorObj =" + fileProcessorObj + ", primeDetectrResultsObj ="
				+ primeDetectrResultsObj + ", isPrimeObj =" + isPrimeObj + ")";
	}
}
