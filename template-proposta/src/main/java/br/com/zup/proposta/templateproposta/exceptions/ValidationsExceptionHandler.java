package br.com.zup.proposta.templateproposta.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ValidationsExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handlers(MethodArgumentNotValidException exception){
        Collection<String> list = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e->{
            String message = String.format("Campo %s %s", e.getField(),messageSource.getMessage(e, LocaleContextHolder.getLocale()));
            list.add(message);
        });

        ValidationErrorsDto error = new ValidationErrorsDto(list);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ValidationErrorsDto> bindExceptions(BindException exception){
        Collection<String> list = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            list.add(message);
        });

        ValidationErrorsDto error = new ValidationErrorsDto(list);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ValidationErrorsDto> handlerApiException(ApiErrorException exception){
        Collection<String> list = new ArrayList<>();
        list.add(exception.getReason());

        ValidationErrorsDto error = new ValidationErrorsDto(list);
        return ResponseEntity.status(exception.getHttpStatus()).body(error);
    }
}
