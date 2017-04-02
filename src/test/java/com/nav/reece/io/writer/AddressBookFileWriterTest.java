package com.nav.reece.io.writer;

import com.nav.reece.Exception.InvalidAddressBookDataException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AddressBookFileWriterTest {
    public DataWriter classUnderTest;

    @org.junit.Test
    public void shouldWriteToFileSuccessfullyWhenValidFilePathPassed() throws
            Exception {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testWriter.txt")
                .getFile());

        classUnderTest = new AddressBookFileWriter(file.getAbsolutePath());
        //when
        List<String> contacts = new ArrayList<String>();
        contacts.add("Nav,123123,123123,book1");
        contacts.add("NavNel,123123,123123,book1");
        contacts.add("Reece Davidson,123123,,book1");
        boolean result = classUnderTest.writeData(contacts);
        //then
        assertTrue(result);
    }

    @org.junit.Test (expected = InvalidAddressBookDataException.class)
    public void
    shouldThrowErrorWhenFilePathThatDoesNotExistPassed() throws
            Exception {
        //given
        classUnderTest = new AddressBookFileWriter("InvalidFilePath.txt");
        //when
        List<String> contacts = new ArrayList<String>();
        contacts.add("Nav,123123,123123,book1");
        contacts.add("NavNel,123123,123123,book1");
        contacts.add("Reece Davidson,123123,,book1");
        boolean result = classUnderTest.writeData(contacts);
        //then
        //throw error;
    }


}