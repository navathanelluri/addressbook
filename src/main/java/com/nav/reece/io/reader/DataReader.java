package com.nav.reece.io.reader;

import com.nav.reece.Exception.InvalidAddressBookDataException;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<String> readData() throws
            InvalidAddressBookDataException, IOException;
}
