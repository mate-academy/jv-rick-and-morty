package mate.academy.rickandmorty.exception;

public class NoSuchCharacterException extends RuntimeException {
    public NoSuchCharacterException(String message) {
        super(message);
    }
}
