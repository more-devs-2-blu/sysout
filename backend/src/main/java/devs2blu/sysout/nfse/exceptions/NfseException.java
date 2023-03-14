package devs2blu.sysout.nfse.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NfseException {
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorHandler> handle(MethodArgumentNotValidException ex){

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ErrorHandler> errorHandlers = new ArrayList<>();

        fieldErrors.forEach(error -> errorHandlers.add(new ErrorHandler(error.getField(), error.getDefaultMessage())));

        return errorHandlers;
    }

}
