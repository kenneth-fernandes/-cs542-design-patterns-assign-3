package prime.util;

/**
 * The class MyLogger is for implementing the logger
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class MyLogger
{

	/**
	 * Enum for to define different log levels
	 */
	public static enum DebugLevel
	{
		CONSTRUCTOR,
		THREAD_RUN,
		RESULTS_ADDED,
		RESULTS,
		NO_OUTPUT,
		NONE
	};

	private static DebugLevel debugValue;

	/**
	 * Use the DEBUG_VALUE in the following way:
	 * DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
	 * DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
	 * DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the Results data structure]
	 * DEBUG_VALUE=1 [Print to stdout the contents of the data structure in the store Results instance]
	 * DEBUG_VALUE=0 [No output should be printed from the application, except the line "The sume of all the prime numbers is: XYZ" ]
	 * @param levelIn
	 */
	public static void setDebugValue(int level)
	{
		switch(level)
		{
			
			case 4:
				debugValue = DebugLevel.CONSTRUCTOR;
				break;
			case 3:
				debugValue = DebugLevel.THREAD_RUN;
				break;
			case 2:
				debugValue = DebugLevel.RESULTS_ADDED;
				break;
			case 1:
				debugValue = DebugLevel.RESULTS;
				break;
			case 0:
				debugValue = DebugLevel.NO_OUTPUT;
				break;
			default:
				debugValue = DebugLevel.NONE;
				break;
		}
	}

	/**
	 * This is a Function for setting the debug level on the basis of
	 *  the Enum value passed
	 */
	public static void setDebugValue(DebugLevel level)
	{
		debugValue = level;
	}

	/**
	 * This function prints the message for corresponding 
	 *  debug value 
	 */
	public static void writeMessage(String Msg, DebugLevel level)
	{
		if (level == debugValue)
			System.out.println(Msg);
	}
	/**
	 * This function prints the error message for corresponding 
	 *  debug value 
	 */
	public static void writeExceptionMessage(String errMsg, DebugLevel level)
	{
		if (level == debugValue)
			System.err.println(errMsg);
	}
	
	/**
	 * This function prints the error object (stack trace) for corresponding 
	 *  debug value 
	 */
	public static void writeExceptionMessage(Exception e, DebugLevel level)
	{
		if (level == debugValue)
			e.printStackTrace();
	}
	
	@Override
	public String toString()
	{
		return "The debug value set is " + debugValue;
	}
}

