package com.example.kcspringmavenapp.exceptions;

import com.example.kcspringmavenapp.exceptions.classes.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            System.out.println(error.getField() + ": " + error.getDefaultMessage());
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//
//        System.out.println("---------------");
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            System.out.println(error.getObjectName() + ": " + error.getDefaultMessage());
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }

//        ApiError apiError =
//                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return handleExceptionInternal(
//                ex, apiError, headers, apiError.getStatus(), request);

//
//        return ResponseEntity.status(status).body(new HashMap<>(){{
//            put("status", "error");
//            put("message", !ex.getBindingResult().getFieldErrors().isEmpty() ? "" + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() : "something went wrong");
//            put("data", null);
//        }});

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("Api Exception")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(!ex.getBindingResult().getFieldErrors().isEmpty() ? "" + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() : "something went wrong")
                        .build()));
//
//        return ResponseEntity.status(status).body(Map.of("error",
//                ExceptionDTO.builder()
////                        .name("Api Exception")
//                        .status(status)
//                        .message(!ex.getBindingResult().getFieldErrors().isEmpty() ? "" + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() : "something went wrong")
//                        .build()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleException(ApiException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                ExceptionDTO.builder()
                        .name("Api Exception")
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()));
    }
}
