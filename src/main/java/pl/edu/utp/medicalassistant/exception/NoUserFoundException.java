package pl.edu.utp.medicalassistant.exception;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(String message) {
        super(message);
    }
}
