package com.lukasw.jan.element.core;

/**
 * Base exception for Jan element library.
 */
public class JanElementException extends RuntimeException {


    public JanElementException(final String message) {
        super(message);
    }

    public JanElementException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
