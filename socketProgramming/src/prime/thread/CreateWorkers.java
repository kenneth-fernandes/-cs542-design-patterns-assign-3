package prime.thread;

import java.io.IOException;

import prime.calculation.IsPrime;
import prime.result.Results;
import prime.util.FileProcessor;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * The CreateWorkers Class contains startWorkers() method which
 * borrow NUM_THREADS threads
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class CreateWorkers
{
	private static CreateWorkers createWrkrsObj = new CreateWorkers(); 
	private FileProcessor fp;
    private IsPrime isPrime;
    private Results results;
	
    private CreateWorkers() {
		MyLogger.writeMessage("CreateWorkers()", DebugLevel.CONSTRUCTOR); 
	} 
  
	/**
	 * The function returns the single object of CreateWorkers class
	 * @param fpIn - The FileProcessor class object
	 * @param resultsIn - The Results class object
	 * @param isPrimeIn - The IsPrime class object
	 * @return - The CreateWorkers class object
	 */
	public static CreateWorkers getInstance(FileProcessor fpIn, Results resultsIn, IsPrime isPrimeIn) 
    {
		createWrkrsObj.fp = fpIn;
        createWrkrsObj.isPrime = isPrimeIn;
		createWrkrsObj.results = resultsIn;
        return createWrkrsObj; 
	}
	
	/**
	 * The method borrows NUM_THREADS threads i.e start them and join on them. 
     	 * The instances of FileProcessor, Results, and IsPrime should be passed to he constructor of the worker thread class.
	 * @param numOfThreads The number of threads passed as command line argument to the program
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void startWorkers(int numOfThreads) throws InterruptedException, IOException
	{
		/**
         	* To Call the createThreads function with required set of parameters
         	*/
		ThreadPool.createThreads(numOfThreads, fp, results, isPrime);
		
		/*Borrowing of the threads, one thread at a time and
		 starting the thread and also performing join on
		 the thread*/
		for(int i = 0; i < numOfThreads; i++)
		{
			Runnable runnable = ThreadPool.borrowThread();
			if(runnable != null)
			{
				((Thread) runnable).start();
				try
				{
					((Thread) runnable).join();
				}
				catch(InterruptedException e)
				{
					throw e;
				}
			}
		}
		
		//File Closing
		try
		{
			fp.closeFile();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	@Override
	public String toString()
	{
		return "CreateWorkers (fp=" + fp + ", results=" + results
				+ ", isPrime=" + isPrime + ")";
	}
}