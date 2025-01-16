package mate.academy.rickandmorty.exeption;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundExceptions(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MalformedUrlException.class)
    public ResponseEntity<String> handleMalformedUrlExceptions(MalformedUrlException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CharacterClientException.class)
    public ResponseEntity<String> handleClientExceptions(CharacterClientException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getMessage().contains("Failed to fetch data")) {
            status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(ex.getMessage(), status);
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return field + ": " + message;
        }
        return e.getDefaultMessage();
    }
}
