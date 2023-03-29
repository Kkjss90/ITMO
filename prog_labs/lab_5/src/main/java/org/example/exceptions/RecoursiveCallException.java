package org.example.exceptions;

/**
 * Исключение, выбрасываемое когда script совершает рекурсивный вызов или вызов по циклу.
 */
public class RecoursiveCallException extends RuntimeException {

    public RecoursiveCallException() {

    }
}
