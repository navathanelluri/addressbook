package com.nav.reece.parser;

import com.nav.reece.io.reader.DataReader;
import com.nav.reece.io.writer.DataWriter;
import com.nav.reece.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.*;

public class AddressBookCSVDataParserTest {
    public DataParser classUnderTest;
    @Mock
    public DataReader reader;
    @Mock
    public DataWriter writer;

    @Before
    public void setUp() throws Exception {
        //given
        initMocks(this);
        classUnderTest = new AddressBookCSVDataParser(reader,writer);
    }

    @Test
    public void shouldParseContactsAndWriteToFileWhenValidDataPassed() throws
            Exception {
        //given
        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("abc","123","","book1"));//when
        //when
        classUnderTest.parseAndSaveToFile(contacts);
        //then
        Mockito.verify(writer).writeData(new ArrayList<String>
                (Arrays.asList("abc,123,,book1")));
    }

    @Test
    public void shouldNotFailWhenEmptyDataPassed() throws
            Exception {
        //given
        List<Contact> contacts = new ArrayList<Contact>();
        //when
        classUnderTest.parseAndSaveToFile(contacts);
        //then
        Mockito.verify(writer).writeData(new ArrayList<String>
                (Arrays.asList()));
    }

    @Test
    public void shouldParseAndSaveToFileWithValidData() throws Exception {
        //given
        given(reader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("abc,123,12,book1", "xyz,321,,book2")));
        //when
        List<Contact> contacts = classUnderTest.readFromFileAndParse();
        //then
        assertEquals(2,contacts.size());
        assertEquals("abc",contacts.get(0).getName());
        assertEquals("123",contacts.get(0).getPrimaryPhoneNumber());
        assertEquals("12",contacts.get(0).getSecondaryPhoneNumber());
        assertEquals("book1",contacts.get(0).getAddressBookName());

    }

}