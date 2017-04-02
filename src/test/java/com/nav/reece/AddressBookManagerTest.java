package com.nav.reece;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.model.Contact;
import com.nav.reece.parser.AddressBookCSVDataParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddressBookManagerTest {
    public AddressBookManager classUnderTest;
    @Mock
    public AddressBookCSVDataParser addressBookCSVDataParser;
    @Mock
    public PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldLoadContactsSuccessfullyWhenRequested() throws Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        Mockito.doReturn(contacts).when(addressBookCSVDataParser)
                .readFromFileAndParse();
        //when
        List<Contact> actualContacts = classUnderTest.loadContacts();
        //then
        assertEquals(contacts,actualContacts);
    }

    @Test
    public void shouldSaveContactsSuccessfully() throws Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        //when
        classUnderTest.saveContacts(contacts);
        //then
        Mockito.verify(addressBookCSVDataParser).parseAndSaveToFile(contacts);
    }

    @Test(expected = InvalidAddressBookDataException.class)
    public void shouldThrowExceptionWhenDuplicateEntryBeingAdded() throws
            Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        int sizeBeforeAddingNew = contacts.size();
        //when
        classUnderTest.addContact(contacts,"abc","123","132","book1");
        //then
        //throws Exception
    }

    @Test
    public void shouldAddContactSuccessfullyWhenValidDataPassedIn() throws
            Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        int sizeBeforeAddingNew = contacts.size();
        //when
        classUnderTest.addContact(contacts,"xyz","123","132","book1");
        //then
        assertEquals(sizeBeforeAddingNew+1,contacts.size());
    }

    @Test
    public void shouldNotRemoveAnyContactWhenNotMatchingNamePassedIn() throws
            Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        //when
        boolean removed = classUnderTest.removeContact(contacts,"xyz");
        //then
        assertFalse(removed);
    }
    @Test
    public void shouldRemoveContactWhenMatchingNamePassedIn() throws
            Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        int sizeBeforeRemoving = contacts.size();
        //when
        boolean removed = classUnderTest.removeContact(contacts,"abc");
        //then
        assertTrue(removed);
        assertEquals(sizeBeforeRemoving-1,contacts.size());
    }

    @Test
    public void printContacts() throws Exception {
        //given
        classUnderTest = new AddressBookManager(addressBookCSVDataParser);
        List<Contact> contacts = mockContacts();
        //when
        classUnderTest.printContacts(contacts,printStream);
        //then
        Mockito.verify(printStream).println(Mockito.contains("book1"));
    }

    public static List<Contact> mockContacts() {
        List<Contact> contacts = new ArrayList<Contact>();

        contacts.add(new Contact(CommandProcessorTest.abc,"1234","123","book1"));
        return contacts;
    }
}