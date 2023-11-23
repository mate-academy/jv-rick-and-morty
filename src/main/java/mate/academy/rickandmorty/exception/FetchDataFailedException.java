package mate.academy.rickandmorty.exception;

public class FetchDataFailedException extends RuntimeException {
    public FetchDataFailedException(String message) {
        super(message);
    }
}
