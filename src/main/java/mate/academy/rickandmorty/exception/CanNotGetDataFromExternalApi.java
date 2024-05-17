package mate.academy.rickandmorty.exception;

public class CanNotGetDataFromExternalApi extends RuntimeException {
    public CanNotGetDataFromExternalApi(String message, Exception e) {
        super(message);
    }
}
