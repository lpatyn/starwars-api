package com.starwars.StarWars.exceptions;

import com.starwars.StarWars.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@ControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(new ErrorDTO(404, "Character not found", "" + e.getValue(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> illegalArgument(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(new ErrorDTO(400, "Invalid id", Objects.requireNonNull(e.getValue()).toString(), "Value of id must be numeric"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFound(NoResourceFoundException e) {
        return new ResponseEntity<>(new ErrorDTO(404, "Resource not found", "/" + e.getResourcePath(), "Requested resource not available"), HttpStatus.NOT_FOUND);
    }

}
