package com.nav.reece.parser;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.model.Contact;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DataParser {
    List<Contact> readFromFileAndParse() throws
            InvalidAddressBookDataException, IOException;

    void parseAndSaveToFile(List<Contact> contacts) throws
            InvalidAddressBookDataException, IOException;
}
