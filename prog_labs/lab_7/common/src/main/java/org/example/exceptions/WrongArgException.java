package org.example.exceptions;

public class WrongArgException extends Exception {
    public WrongArgException(String wrong) {
        System.out.print(wrong);
    }
}
