package com.rexdelf.issuetrackerapp.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(String message){
      super(message);
  }
}
