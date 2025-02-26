package exception;

import java.util.InputMismatchException;

public class NotNumberException extends InputMismatchException{
    public NotNumberException(String message){
        super(message);
    }
}
