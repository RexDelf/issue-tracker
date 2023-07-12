package com.rexdelf.issuetrackerapp.exceptions;

public class NotFoundException extends RuntimeException{
  public NotFoundException(String message){
    super(message);
  }
}
