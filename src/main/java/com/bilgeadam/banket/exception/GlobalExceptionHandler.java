package com.bilgeadam.banket.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BanketApplicationException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleBanketApplicationException(BanketApplicationException exception){
        ErrorMessage errorMessage = createError(exception.getErrorType(), exception);
        errorMessage.addField(exception.getMessage());
        return ResponseEntity.
                status(exception.getErrorType().getHttpStatus()).
                body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorType errorType = ErrorType.PARAMETER_NOT_VALID;
        List<String> fields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e-> fields.add(e.getField()+": " + e.getDefaultMessage()));
        ErrorMessage errorMessage=createError(errorType,ex);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,errorType.getHttpStatus());
    }

/*
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.ok(ErrorMessage.builder()
                .code(ErrorType.PARAMETER_NOT_VALID)
                .message("Runtime Error : "+exception.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
        ErrorMessage errorMessage = createError(ErrorType.PARAMETER_NOT_VALID, exception);
        errorMessage.addField(exception.getMessage());
        return ResponseEntity
                .status(errorMessage.getCode().getHttpStatus())
                .body(errorMessage);
    }
*/

    private ErrorMessage createError(ErrorType errorType, Exception ex){
        return ErrorMessage.builder()
                .code(errorType)
                .message(errorType.getMessage())
                .build();
    }

}
