package com.nav.reece.Exception;

public class InvalidAddressBookDataException extends Exception{
    public InvalidAddressBookDataException(String message) {
        super(message);
    }

    public InvalidAddressBookDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
