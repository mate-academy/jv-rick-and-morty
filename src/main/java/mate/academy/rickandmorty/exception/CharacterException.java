package mate.academy.rickandmorty.exception;

public class CharacterException extends RuntimeException {
    public CharacterException() {
    }

    public CharacterException(String message) {
        super(message);
    }

    public CharacterException(String message, Throwable cause) {
        super(message, cause);
    }
}
