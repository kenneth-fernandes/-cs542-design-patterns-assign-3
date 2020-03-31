package persister.driver;

import persister.util.InputParametersData;
import persister.util.InputParametersI;
import persister.socket.PersistService;

/**
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class Driver {
	public static void main(String[] args) throws InterruptedException {
		try {
			InputParametersI inputParamsObj = InputParametersData.getInstance();
			
			/*
			 * As the build.xml specifies the arguments as argX, in case the argument value
			 * is not given java takes the default value specified in build.xml. To avoid
			 * that, below condition is used
			 */
			if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
				System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
				System.exit(0);
			} else {
				inputParamsObj.setPersistSvcPortNum(args[1]);
				inputParamsObj.setOutputFilePath(args[0]);
			}

			PersistService persistSvcObj = PersistService.getInstance(inputParamsObj.getPersistSvcPortNum(),
					inputParamsObj.getOutputFilePath());

			persistSvcObj.initSocketConnection();
			persistSvcObj.processData();
			persistSvcObj.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}