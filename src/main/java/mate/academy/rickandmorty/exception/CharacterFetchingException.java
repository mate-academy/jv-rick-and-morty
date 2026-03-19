package mate.academy.rickandmorty.exception;

public class CharacterFetchingException extends RuntimeException {
    public CharacterFetchingException(String message) {
        super(message);
    }

    public CharacterFetchingException(Throwable cause) {
        super(cause);
    }
}
