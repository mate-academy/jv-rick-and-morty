package mate.academy.rickandmorty.exception;

public class InitializedDatabaseException extends RuntimeException {
    public InitializedDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
