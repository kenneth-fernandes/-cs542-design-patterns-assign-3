package socketProgramming.src.socketProgramming.util;

import socketProgramming.src.socketProgramming.util.MyLogger.DebugLevel;

/**
 * The class IsPrime checks whether a given number is prime
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class IsPrime {
    public IsPrime() {
        MyLogger.writeMessage("IsPrime()", DebugLevel.CONSTRUCTOR);
    }

    /**
     * This is a function to check whether a given number is prime or not
     * 
     * @param The number "n" is to be checked for prime
     * @return the return value determines whether "n" is prime
     */
    public boolean checkNum(int n) {
        int flag = 0;
        if (n <= 1)
            return false;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {

                flag = 1;
                return false;
            }
        }
        if (flag == 0)
            return true;
        return true;

    }
}