package mate.academy.rickandmorty.exception;

public class CharacterExternalLoadException extends RuntimeException {
    public CharacterExternalLoadException(String exceptionDescription, Throwable exception) {
        super(exceptionDescription, exception);
    }
}
