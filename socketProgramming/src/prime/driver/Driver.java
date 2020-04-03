package prime.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import prime.thread.CreateWorkers;
import prime.util.FileProcessor;
import prime.util.FileProcessorI;
import prime.util.InputParametersI;
import prime.calculation.IsPrime;
import prime.calculation.IsPrimeI;
import prime.util.MyLogger;
import prime.util.PrimeDetectorInput;
import prime.result.ResultsI;
import prime.result.PrimeDetectrResults;
import prime.util.MyLogger.DebugLevel;

/**
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Driver {
	public static void main(String[] args) throws InterruptedException {
		try {
			InputParametersI inputParamsObj = PrimeDetectorInput.getInstance();
			/*
			 * As the build.xml specifies the arguments as argX, in case the argument value
			 * is not given java takes the default value specified in build.xml. To avoid
			 * that, below condition is used
			 */
			if (args.length != 6 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
					|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[5].equals("${arg5}")) {
				System.err.println("\nError: Incorrect number of arguments. Program accepts 6 argumnets.");
				System.exit(0);
			} else {

				inputParamsObj.setInputFilePath(args[0]);
				inputParamsObj.setNumOfThreads(args[1]);
				inputParamsObj.setResultDataCapacity(args[2]);
				inputParamsObj.setPersistSvcIPAddr(args[3]);
				inputParamsObj.setPersistSvcPortNum(args[4]);
				inputParamsObj.setDebugValue(args[5]);
			}

			// To check if the number of threads is between 1 and 5
			if (!(inputParamsObj.getNumOfThreads() >= 1 && inputParamsObj.getNumOfThreads() <= 5)) {
				System.err.println(
						"\nError: Incorrect value of NUM_THREADS. The expected value of NUM_THREADS is between 1 and 5.");
				System.exit(0);
			}
			// To check if theport number is between less than 32768 or greater than 50000
			if (!(inputParamsObj.getPersistSvcPortNum() < 32768 || inputParamsObj.getPersistSvcPortNum() > 50000)) {
				System.err.println("\nError: Incorrect value of DEBUG_VALUE. The expected value of Port number is"
						+ " less than 32768 or greater than 50000.");
				System.exit(0);
			}
			// To check if the debug value is between 0 and 4
			if (!(inputParamsObj.getDebugValue() >= 0 && inputParamsObj.getDebugValue() <= 4)) {
				System.err.println(
						"\nError: Incorrect value of DEBUG_VALUE. The expected value of NUM_THREADS is 0 and 4.");
				System.exit(0);
			}
			// Setting the debug value based on the input
			MyLogger.setDebugValue(inputParamsObj.getDebugValue());
			/**
			 * Creating instances of FileProcessor, PrimeDetectrResults, IsPrime
			 */
			FileProcessorI fileProcessorObj = new FileProcessor(inputParamsObj.getInputFilePath());
			ResultsI primeDetectrResultsObj = PrimeDetectrResults.getInstance();
			IsPrimeI isPrimeObj = IsPrime.getInstance();

			/**
			 * Creating instance of worker threads where input parameters are instances of
			 * FileProcessor, PrimeDetectrResults, IsPrime
			 */
			CreateWorkers workers = CreateWorkers.getInstance(fileProcessorObj, primeDetectrResultsObj, isPrimeObj);
			workers.startWorkers(inputParamsObj.getNumOfThreads());

			/**
			 * Logging the messages to stdout
			 */
			MyLogger.writeMessage(primeDetectrResultsObj.toString(), DebugLevel.RESULTS);

			MyLogger.writeMessage(
					"\nThe sum of all the prime numbers is: " + primeDetectrResultsObj.getSumOfPrimeNumbers(),
					DebugLevel.NO_OUTPUT);

		} catch (NumberFormatException e) {
			System.err.println("Error: Please enter number as an input  for NUM_THREADS and DEBUG_VALUE");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		} catch (IOException e) {
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		} catch (InterruptedException e) {
			MyLogger.writeExceptionMessage(e, DebugLevel.NO_OUTPUT);
		}
	}
}