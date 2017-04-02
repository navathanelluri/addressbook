package com.nav.reece.io.writer;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.model.Contact;

import java.io.IOException;
import java.util.List;

public interface DataWriter {
    boolean writeData(List<String> contacts) throws
            InvalidAddressBookDataException,IOException;
}
