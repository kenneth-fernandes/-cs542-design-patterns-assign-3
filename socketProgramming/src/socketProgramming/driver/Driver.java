package socketProgramming.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import socketProgramming.util.CreateWorkers;
import socketProgramming.util.FileProcessor;
import socketProgramming.util.InputParametersData;
import socketProgramming.util.IsPrime;
import socketProgramming.util.MyLogger;
import socketProgramming.util.Results;
import socketProgramming.util.MyLogger.DebugLevel;

/**
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Driver {
	public static void main(String[] args) throws InterruptedException {
		try {
			InputParametersData inputParamsDataObj = InputParametersData.getInstance();
			/*
			 * As the build.xml specifies the arguments as argX, in case the argument value
			 * is not given java takes the default value specified in build.xml. To avoid
			 * that, below condition is used
			 */
			if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
					|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[5].equals("${arg5}")) {
				System.err.println("Error: Incorrect number of arguments. Program accepts 6 argumnets.");
				System.exit(0);
			} else {

				inputParamsDataObj.setInputFilePath(args[0]);
				inputParamsDataObj.setNumOfThreads(args[1]);
				inputParamsDataObj.setResultDataCapacity(args[2]);
				inputParamsDataObj.setPersistSvcIPAddr(args[3]);
				inputParamsDataObj.setPersistSvcPortNum(args[4]);
				inputParamsDataObj.setDebugValue(args[5]);
			}

			// To check if the number of threads is between 1 and 5
			if (!(inputParamsDataObj.getNumOfThreads() >= 1 && inputParamsDataObj.getNumOfThreads() <= 5)) {
				System.err.println(
						"Error: Incorrect value of NUM_THREADS. The expected value of NUM_THREADS is between 1 and 5.");
				System.exit(0);
			}

			// To check if the debug value is between 0 and 4
			if (!(inputParamsDataObj.getDebugValue() >= 0 && inputParamsDataObj.getDebugValue() <= 4)) {
				System.err.println(
						"Error: Incorrect value of DEBUG_VALUE. The expected value of NUM_THREADS is 0 and 4.");
				System.exit(0);
			}

			MyLogger.setDebugValue(inputParamsDataObj.getDebugValue());

			FileProcessor fp = new FileProcessor(inputParamsDataObj.getInputFilePath());
			Results results = new Results();
			IsPrime isPrime = new IsPrime();

			CreateWorkers workers = CreateWorkers.getInstance(fp, results, isPrime);
			workers.startWorkers(inputParamsDataObj.getNumOfThreads());

			MyLogger.writeMessage(results.toString(), DebugLevel.RESULTS);

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