package exception;

import java.util.InputMismatchException;

public class WrongMenuOptionException extends InputMismatchException{
    public WrongMenuOptionException(String message){
        super(message);
    }
}
