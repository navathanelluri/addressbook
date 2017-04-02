package com.nav.reece.io.reader;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddressBookFileReaderTest {
    public DataReader classUnderTest;

    @Test
    public void shouldReadDataSuccesfullyWhenValidFilePassed() throws
            Exception {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testReader.txt").getFile());
        classUnderTest = new AddressBookFileReader(file.getAbsolutePath());
        //when
        List<String> contacts = classUnderTest.readData();
        //then
        assertNotNull(contacts);
        assertEquals(2,contacts.size());
    }

    @Test (expected = InvalidAddressBookDataException.class)
    public void shouldThrowErrorWhenInvalidDataFilePassed() throws
            InvalidAddressBookDataException, IOException {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testReaderInvalid.txt")
                .getFile
                ());
        classUnderTest = new AddressBookFileReader(file.getAbsolutePath());
        //when
        List<String> contacts = classUnderTest.readData();
        //then throws error
    }

    @Test (expected = IOException.class)
    public void shouldThrowErrorWhenFileThatDoesNotExistPassed() throws
            InvalidAddressBookDataException, IOException {
        //given
        classUnderTest = new AddressBookFileReader
                ("testReaderThatDoesNotExist.txt");
        //when
        List<String> contacts = classUnderTest.readData();
        //then throws error
    }

}