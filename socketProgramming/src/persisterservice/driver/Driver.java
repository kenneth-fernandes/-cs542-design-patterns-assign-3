package persisterservice.driver;

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
            }
		}
}