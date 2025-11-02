package model.exception;

import java.util.InputMismatchException;

public class InvalidInput extends InputMismatchException {
    public InvalidInput(String message) {
        super(message);
    }
}
