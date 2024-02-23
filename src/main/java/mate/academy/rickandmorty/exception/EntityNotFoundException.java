package mate.academy.rickandmorty.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String massage){
        super(massage);
    }
}
