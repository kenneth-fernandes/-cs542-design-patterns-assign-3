package socketProgramming.src.socketProgramming.util;


public class MyLogger
{

	
	public static enum DebugLevel
	{
		CONSTRUCTOR,
		THREAD_RUN,
		RESULTS_ENTRY_ADDED,
		RESULTS_CONTENT,
		NO_OUTPUT,
		NONE
	};

	private static DebugLevel debugValue;

	
	public static void setDebugValue(int levelIn)
	{
		switch(levelIn)
		{
			case 4:
				debugValue = DebugLevel.CONSTRUCTOR;
				break;
			case 3:
				debugValue = DebugLevel.THREAD_RUN;
				break;
			case 2:
				debugValue = DebugLevel.RESULTS_ENTRY_ADDED;
				break;
			case 1:
				debugValue = DebugLevel.RESULTS_CONTENT;
				break;
			case 0:
				debugValue = DebugLevel.NO_OUTPUT;
				break;
			default:
				debugValue = DebugLevel.NONE;
				break;
		}
	}

	
	public static void setDebugValue(DebugLevel levelIn)
	{
		debugValue = levelIn;
	}

	
	public static void writeMessage(String message, DebugLevel levelIn)
	{
		if (levelIn == debugValue)
			System.out.println(message);
	}

	public static void writeExceptionMessage(String message, DebugLevel levelIn)
	{
		if (levelIn == debugValue)
			System.err.println(message);
	}
	
	
	public static void writeExceptionMessage(Exception e, DebugLevel levelIn)
	{
		if (levelIn == debugValue)
			e.printStackTrace();
	}
	
	public String toString()
	{
		return "The debug value set is " + debugValue;
	}
}