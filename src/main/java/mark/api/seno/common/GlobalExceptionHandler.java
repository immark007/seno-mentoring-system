package mark.api.seno.common;

import mark.api.seno.dto.ErroField;
import mark.api.seno.dto.ErrorResponse;
import mark.api.seno.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErroField> listError = fieldErrors.stream().map(e -> new ErroField(e.getField(), e.getDefaultMessage())).toList();


        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Error of validation", listError
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ErrorResponse.conflict(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleErrosNaoTratados(RuntimeException e){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu, um erro inesperado, entre em contato com a adminstração do sistema",List.of());
    }
}
