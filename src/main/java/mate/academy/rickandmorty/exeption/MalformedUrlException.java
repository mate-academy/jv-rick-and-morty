package mate.academy.rickandmorty.exeption;

public class MalformedUrlException extends RuntimeException {
    public MalformedUrlException(String message, Exception e) {
        super(message, e);
    }
}
