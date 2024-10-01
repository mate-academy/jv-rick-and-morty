package mate.academy.rickandmorty.exception;

public class SendRequestException extends RuntimeException {
    public SendRequestException(String message) {
        super(message);
    }

    public SendRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
