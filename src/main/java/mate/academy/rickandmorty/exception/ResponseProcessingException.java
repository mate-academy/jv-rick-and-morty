package mate.academy.rickandmorty.exception;

public class ResponseProcessingException extends RuntimeException {
    public ResponseProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
