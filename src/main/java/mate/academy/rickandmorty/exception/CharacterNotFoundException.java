package mate.academy.rickandmorty.exception;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterNotFoundException(String message) {
        super(message);
    }
}
