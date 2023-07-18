package com.rexdelf.issuetrackerapp.handler;

import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.exceptions.InvalidDateException;
import com.rexdelf.issuetrackerapp.exceptions.ModificationNotAllowedException;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(JsonPatchException.class)
  public ResponseEntity<String> handleJsonPatchException() {
    return new ResponseEntity<>("Failed to apply JSON patch", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidDateException.class)
  public ResponseEntity<String> handleBadRequest(InvalidDateException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ModificationNotAllowedException.class)
  public ResponseEntity<String> handleForbidden(InvalidDateException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
  }
}