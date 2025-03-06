package mark.api.seno.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status, String message, List<ErroField> errors) {

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }

    public static ErrorResponse badRequest(String message){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }
}
