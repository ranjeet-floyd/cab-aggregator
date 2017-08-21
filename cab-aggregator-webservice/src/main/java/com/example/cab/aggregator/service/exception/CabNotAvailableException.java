package com.example.cab.aggregator.service.exception;

/**
 *
 * @author ranjeet
 */
public class CabNotAvailableException extends Exception {

    private static final long serialVersionUID = 1386338357425427532L;
    
    public CabNotAvailableException(String message) {
        super(message);
    }
    
}
