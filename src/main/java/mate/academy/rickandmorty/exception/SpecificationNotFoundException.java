package mate.academy.rickandmorty.exception;

public class SpecificationNotFoundException extends RuntimeException {
    public SpecificationNotFoundException(String message) {
        super(message);
    }
}
