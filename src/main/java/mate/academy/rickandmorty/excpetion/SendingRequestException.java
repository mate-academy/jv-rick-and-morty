package mate.academy.rickandmorty.excpetion;

public class SendingRequestException extends RuntimeException {
    public SendingRequestException(String message) {
        super(message);
    }
}
