package com.nav.reece;

import com.nav.reece.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandProcessorTest {
    public static final String abc = "abc";
    public CommandProcessor classUnderTest;

    public InputStream inputStream;

    @Mock
    public PrintStream printStream;
    @Mock
    public AddressBookManager addressBookManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldAddNewContactSuccessfullyWhenValidContactDataPassed()
            throws
            Exception {
        //given
        InputStream mockInputStream = new ByteArrayInputStream(("ADD\nabc,123," +
                "123,book1").getBytes
                ());
        classUnderTest = new CommandProcessor(mockInputStream,printStream,
                addressBookManager);
        //when
        classUnderTest.run( null);
        //then
        Mockito.verify(printStream, atLeastOnce()).println(CommandProcessor
                .COMMAND_OPTIONS_HELP_TEXT);
        Mockito.verify(printStream, times(1)).println(CommandProcessor
                .ADD_COMMAND_HELP_TEXT);
        Mockito.verify(printStream,times(1)).println(CommandProcessor.SUCCESSFULLY_ADDED_CONTACT);
    }

    @Test
    public void
    shouldPrintValidationRulesMessageWhenInvalidContactDataPassed()
            throws
            Exception {
        //given
        InputStream mockInputStream = new ByteArrayInputStream(("ADD\nabc2," +
                "0012345678900," +
                "00123,book1").getBytes
                ());
        classUnderTest = new CommandProcessor(mockInputStream,printStream,
                addressBookManager);
        //when
        classUnderTest.run( null);
        //then
        Mockito.verify(printStream, atLeastOnce()).println(CommandProcessor
                .COMMAND_OPTIONS_HELP_TEXT);
        Mockito.verify(printStream, times(1)).println(CommandProcessor
                .ADD_COMMAND_HELP_TEXT);
        Mockito.verify(printStream,times(0)).println(CommandProcessor
                .SUCCESSFULLY_ADDED_CONTACT);
        Mockito.verify(printStream,times(1)).println(CommandProcessor
                .ADD_COMMAND_INPUT_VALIDATION_RULES_HELP_TEXT);

    }

    @Test
    public void
    shouldPrintContactsWhenValidContactDataPassed()
            throws
            Exception {
        //given
        InputStream mockInputStream = new ByteArrayInputStream(("PRINT\nabc2," +
                "0012345678900," +
                "00123,book1").getBytes
                ());
        classUnderTest = new CommandProcessor(mockInputStream,printStream,
                addressBookManager);
        //when
        List<Contact> contacts = AddressBookManagerTest.mockContacts();
        Mockito.doReturn(contacts).when(addressBookManager).loadContacts();
        classUnderTest.run( null);
        //then
        Mockito.verify(printStream, atLeastOnce()).println(CommandProcessor
                .COMMAND_OPTIONS_HELP_TEXT);
        Mockito.verify(addressBookManager,times(1)).printContacts(contacts,
                printStream);

    }

    @Test
    public void
    shouldRemoveContactWhenValidContactNamePassed()
            throws Exception {
        //given
        InputStream mockInputStream = new ByteArrayInputStream(
                ("REMOVE\nabc").getBytes());
        classUnderTest = new CommandProcessor(mockInputStream,printStream,
                addressBookManager);
        //when
        List<Contact> contacts = AddressBookManagerTest.mockContacts();
        Mockito.doReturn(contacts).when(addressBookManager).loadContacts();
        classUnderTest.run( null);
        //then
        Mockito.verify(printStream, atLeastOnce()).println(CommandProcessor
                .COMMAND_OPTIONS_HELP_TEXT);
        Mockito.verify(printStream, atLeastOnce()).println(CommandProcessor
                .REMOVE_COMMAND_HELP_TEXT);
        Mockito.verify(addressBookManager,times(1)).removeContact(contacts,abc);

    }

}