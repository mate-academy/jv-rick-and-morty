package mate.academy.rickandmorty.exception;

public class ApiResponseParsingException extends RuntimeException {
    public ApiResponseParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
