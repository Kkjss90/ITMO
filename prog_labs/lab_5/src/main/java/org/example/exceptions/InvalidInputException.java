package org.example.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String str){
        super(str);
    }
}
