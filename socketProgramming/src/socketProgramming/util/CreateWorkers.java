package socketProgramming.src.socketProgramming.util;

import java.io.IOException;
import socketProgramming.src.socketProgramming.util.MyLogger.DebugLevel;

/**
 * The CreateWorkers Class contains startWorkers() method which
 * borrow NUM_THREADS threads
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class CreateWorkers
{
	private FileProcessor fp;
    private IsPrime isPrime;
    private Results results;
	
	
	public CreateWorkers(FileProcessor fpIn, Results resultsIn, IsPrime isPrimeIn)
	{
		MyLogger.writeMessage("CreateWorkers()", DebugLevel.CONSTRUCTOR);
        this.fp = fpIn;
        this.isPrime = isPrimeIn;
		this.results = resultsIn;
		
	}
	
	/**
	 * The method borrows NUM_THREADS threads i.e start them and join on them. 
     * The instances fo FileProcessor, Results, and IsPrime should be passed to he constructor of the worker thread class.
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