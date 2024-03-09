package mate.academy.rickandmorty.exception;

public class RequestProcessingException extends RuntimeException {
    public RequestProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
