package mate.academy.rickandmorty.exeption;

public class UnableGettingAllCharactersException extends RuntimeException {
    public UnableGettingAllCharactersException(String message, Throwable cause) {
        super(message, cause);
    }
}
