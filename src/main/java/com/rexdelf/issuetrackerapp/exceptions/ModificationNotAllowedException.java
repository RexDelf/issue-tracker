package com.rexdelf.issuetrackerapp.exceptions;

public class ModificationNotAllowedException extends RuntimeException{
    public ModificationNotAllowedException(String message){
      super(message);
    }
}
