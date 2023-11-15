package mate.academy.rickandmorty.exception;

public class BadResponseException extends RuntimeException {
    public BadResponseException(String message) {
        super(message);
    }
}
