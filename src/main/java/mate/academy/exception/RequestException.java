package mate.academy.exception;

public class RequestException extends RuntimeException {
    public RequestException(String message, Exception e) {
        super(message);
    }
}
