package com.example.cab.aggregator.service.exception;

/**
 * If no space available in cab
 *
 * @author ranjeet
 */
public class CabOverflowException extends RuntimeException {
    
    private static final long serialVersionUID = -1797846676258833246L;
    
    public CabOverflowException(String message) {
        super(message);
    }
    
}
