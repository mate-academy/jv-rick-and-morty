package mate.academy.rickandmorty.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
