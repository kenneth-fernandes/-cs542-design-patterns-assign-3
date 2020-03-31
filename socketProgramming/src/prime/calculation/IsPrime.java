package prime.calculation;

import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * The class IsPrime checks whether a given number is prime
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class IsPrime implements IsPrimeI {
    private static IsPrimeI isPrimeObj = new IsPrime();

    private IsPrime() {
        MyLogger.writeMessage("IsPrime()", DebugLevel.CONSTRUCTOR);
    }

    public static IsPrimeI getInstance() {
        return isPrimeObj;
    }

    /**
     * This is a function to check whether a given number is prime or not
     * 
     * @param The number "num" is to be checked for prime
     * @return the return value determines whether "n" is prime
     */
    public boolean checkNum(int num) {
        int flag = 0;
        if (num <= 1)
            return false;

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {

                flag = 1;
                return false;
            }
        }
        if (flag == 0)
            return true;
        return true;

    }

}
