package persister.driver;

import persister.util.InputParametersData;
import persister.util.PersistService;

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
			if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
				System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
				System.exit(0);
			} else {
				inputParamsDataObj.setPersistSvcPortNum(args[1]);
				inputParamsDataObj.setOutputFilePath(args[0]);
			}

			PersistService persistSvcObj = PersistService.getInstance(inputParamsDataObj.getPersistSvcPortNum(),
					inputParamsDataObj.getOutputFilePath());

			persistSvcObj.initSocketConnection();
			persistSvcObj.processData();
			persistSvcObj.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}