package com.example.cab.aggregator.service.exception;

/**
 *
 * @author ranjeet
 */
public class NoCabExistException extends Exception {

    private static final long serialVersionUID = 7684728372399081734L;

    public NoCabExistException(String message) {
        super(message);
    }

}
