package mate.academy.rickandmorty.exceptions;

public class GettingCharactersListException extends RuntimeException {
    public GettingCharactersListException(String message) {
        super(message);
    }

    public GettingCharactersListException(String message, Throwable cause) {
        super(message, cause);
    }
}
