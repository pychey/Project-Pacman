package exception;

import java.util.InputMismatchException;

public class WrongMainMenuOptionException extends InputMismatchException {
    public WrongMainMenuOptionException(String message){
        super(message);
    }
    public WrongMainMenuOptionException(int option){
        if(option != 1 && option != 2 && option != 3 && option != 0){
            throw new WrongMainMenuOptionException("Exception Caught: Option can't be beside 1,2,3");
        }
    }
}
