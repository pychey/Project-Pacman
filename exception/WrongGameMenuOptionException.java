package exception;

import java.util.InputMismatchException;

public class WrongGameMenuOptionException extends InputMismatchException {
    public WrongGameMenuOptionException(String message){
        super(message);
    }
    public WrongGameMenuOptionException(int option){
        if(option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 0){
            throw new WrongMainMenuOptionException("Exception Caught: Option can't be beside 1,2,3,4");
        }
    }
}
