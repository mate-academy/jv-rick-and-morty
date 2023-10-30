package mate.academy.rickandmorty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataProcessingException.class)
    protected ResponseEntity<String> handleEntityNotFoundExceptions(DataProcessingException ex) {
        return new ResponseEntity<>("can't load data: "
                + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
