package mate.academy.rickandmorty.exeption;

public class UnableGettingCountOfCharactersException extends RuntimeException {
    public UnableGettingCountOfCharactersException(String message, Throwable cause) {
        super(message, cause);
    }
}
