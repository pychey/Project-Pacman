package exception;

import java.util.InputMismatchException;

public class WrongMenuOptionException extends InputMismatchException{
    public WrongMenuOptionException(String message){
        super(message);
    }
    public WrongMenuOptionException(int option){
        if(option != 1 && option != 2 && option != 3){
            throw new WrongMenuOptionException("Exception Caught: Option can't be beside 1,2,3");
        }
    }
}
