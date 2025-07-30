package mate.academy.rickandmorty.exception;

public class EmptyLocalDatabaseException extends RuntimeException {
    public EmptyLocalDatabaseException(String message) {
        super(message);
    }
}
