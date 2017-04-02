package com.nav.reece;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.constants.AddressBookConfigConstants;
import com.nav.reece.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandProcessor {

    public static final String WHITE_SPACE = " ";
    public static final String END_OF_LINE = "\n";
    public static final String COMMAND_ADD = "ADD";
    public static final String COMMAND_REMOVE = "REMOVE";
    public static final String COMMAND_PRINT = "PRINT";
    public static final String COMMAND_PRINT_UNIQUE = "PRINT_UNIQUE";
    public static final String COMMAND_EXIT = "EXIT";
    public static final String COMMAND_OPTIONS_HELP_TEXT =
            END_OF_LINE+ "Select one of " +
            "these commands: "+COMMAND_ADD + WHITE_SPACE + COMMAND_REMOVE +
            WHITE_SPACE + COMMAND_PRINT + WHITE_SPACE+COMMAND_PRINT_UNIQUE+
            WHITE_SPACE+COMMAND_EXIT+END_OF_LINE;
    public static final String ADD_COMMAND_HELP_TEXT = END_OF_LINE+
            "Please enter details of new contact in the following format:"+ END_OF_LINE +
            "Name,Primary phone number,Secondary phone number,Address book name"+ END_OF_LINE +
            "Example: Scott Anderson,61401234567,0312345678,Supplier Address Book"
            +END_OF_LINE;
    public static final String ADD_COMMAND_INPUT_VALIDATION_RULES_HELP_TEXT =
            "Validation Rules:"+ END_OF_LINE +
            "1. Name, primary contact number and address book name are mandatory."+ END_OF_LINE +
            "2. Name Validation: Alphabets and spaces upto 40 chars long." +  END_OF_LINE +
            "3. Contact numbers Validation: Between 1 to 10 digits long." + END_OF_LINE +
            "4. Address book name Validation: Alphabets and spaces upto 40 chars long."
                    + END_OF_LINE;
    public static final String REMOVE_COMMAND_HELP_TEXT =END_OF_LINE+
            "Please enter name of the contact to remove" +END_OF_LINE+
            "Example:Scott Anderson" +END_OF_LINE;
    public static final String SUCCESSFULLY_ADDED_CONTACT = "Successfully Added Contact.";

    public InputStream inputStream;
    public PrintStream printStream;
    public AddressBookManager addressBookManager;

    public CommandProcessor(InputStream inputStream, PrintStream printStream, AddressBookManager addressBookManager) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        this.addressBookManager = addressBookManager;
    }

    public void run(String[] commandTypeAndParams) throws Exception{
        Scanner scanner = new Scanner(inputStream);
        printStream.println(COMMAND_OPTIONS_HELP_TEXT);
        List<Contact> contacts = addressBookManager.loadContacts();
        while(scanner.hasNext()) {
            String command = scanner.next();
            processCommand(scanner, command, contacts);
            printStream.println(COMMAND_OPTIONS_HELP_TEXT);
        }
    }

    private void processCommand(Scanner scanner, String command, List<Contact> contacts) throws InvalidAddressBookDataException, IOException {
        switch (command) {
            case COMMAND_ADD:
                printStream.println(ADD_COMMAND_HELP_TEXT);
                String newContact = scanner.next();
                if(newContact==null || !newContact.matches
                        (AddressBookConfigConstants.CONTACT_DATA_CSV_VALIDITY_REGEX_PATTERN)){
                    printStream.println(ADD_COMMAND_INPUT_VALIDATION_RULES_HELP_TEXT);
                }else {
                    List<String> tokens = Arrays.asList(newContact.split(","));
                    addressBookManager.addContact(contacts, tokens.get(0), tokens.get(1),
                            tokens.get(2), tokens.get(3));
                    //addressBookManager.saveContacts(contacts);
                    printStream.println(SUCCESSFULLY_ADDED_CONTACT);
                }
                break;
            case COMMAND_REMOVE:
                printStream.println(REMOVE_COMMAND_HELP_TEXT);
                String contactToRemove = scanner.next();
                boolean removed = addressBookManager.removeContact(contacts,
                        contactToRemove);
                //addressBookManager.saveContacts(contacts);
                if(removed)
                    printStream.println("Removed contacts from address books with" +
                        " name :"+contactToRemove);
                else
                    printStream.println("No contacts in address books found " +
                            "that match name :"+contactToRemove);
                break;
            case COMMAND_PRINT:
                addressBookManager.printContacts(contacts, printStream);
                break;
            case COMMAND_PRINT_UNIQUE:
                addressBookManager.printUniqueContacts(contacts, printStream);
                break;
            case COMMAND_EXIT:
                System.exit(0);
            default:
                printStream.println(COMMAND_OPTIONS_HELP_TEXT);
                break;
        }
    }


}