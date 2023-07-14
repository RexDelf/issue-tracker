package com.rexdelf.issuetrackerapp.handler;

import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import java.io.IOException;
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
  public ResponseEntity<String> handleJsonPatchException(JsonPatchException ex) {
    return new ResponseEntity<>("Failed to apply JSON patch", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    return new ResponseEntity<>("I/O error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}