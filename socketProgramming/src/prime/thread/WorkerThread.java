package prime.thread;

import java.io.IOException;
import java.util.List;

import prime.util.InputParametersI;
import prime.calculation.IsPrimeI;
import prime.result.PrimeDetectrResultsI;
import prime.util.FileProcessor;
import prime.util.MyLogger;
import prime.util.PrimeDetectorInput;
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
	private FileProcessor fileProcessorObj;

	// Stores the handler of IsPrime class
	private IsPrimeI isPrimeObj;

	// Stores the handler of PrimeDetectrResults class
	private PrimeDetectrResultsI primeDetectrResultsObj;

	private InputParametersI inputParamObj = PrimeDetectorInput.getInstance();

	public WorkerThread(FileProcessor fileProcessorInObj, IsPrimeI isPrimeInObj,
			PrimeDetectrResultsI primeDetectrResultsInObj) {
		MyLogger.writeMessage("WorkerThread()", DebugLevel.CONSTRUCTOR);
		this.fileProcessorObj = fileProcessorInObj;
		this.isPrimeObj = isPrimeInObj;
		this.primeDetectrResultsObj = primeDetectrResultsInObj;
	}

	/**
	 * This is a function which Invoke a method in the FileProcessor to retrieve one
	 * line, as string from the input file and Check if it is a prime number. Also
	 * stores the prime number in a data structure in the Results instance
	 */
	@Override
	public void run() {
		MyLogger.writeMessage("run()", DebugLevel.THREAD_RUN);
		try {
			String line = null;
			/*
			 * This reads the file line by line until the end of the file is reached
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
				primeDetectrResultsObj.addTerminationMsg("STOP");
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return "This is a WorkerThread (fileProcessorObj =" + fileProcessorObj + ", primeDetectrResultsObj ="
				+ primeDetectrResultsObj + ", isPrimeObj =" + isPrimeObj + ")";
	}
}
