package mate.academy.rickandmorty.exception;

public class ThirdPartyApiException extends RuntimeException{
    public ThirdPartyApiException(String message, Throwable e) {
        super(message, e);
    }
}
