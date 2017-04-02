package com.nav.reece;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.model.Contact;
import com.nav.reece.parser.AddressBookCSVDataParser;
import com.nav.reece.parser.DataParser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBookManager {
    public DataParser addressBookCSVDataParser;

    public AddressBookManager() {
        this.addressBookCSVDataParser = new AddressBookCSVDataParser();
    }

    public AddressBookManager(DataParser addressBookCSVDataParser) {
        this.addressBookCSVDataParser = addressBookCSVDataParser;
    }


    public List<Contact> loadContacts() throws
            InvalidAddressBookDataException, IOException{
        return addressBookCSVDataParser.readFromFileAndParse();
    }

    public void saveContacts(List<Contact> contacts) throws
            InvalidAddressBookDataException, IOException{
        addressBookCSVDataParser.parseAndSaveToFile(contacts);
    }

    public void addContact(List<Contact> addressBookContacts,String
            name,
                           String primaryPhoneNumber,
                           String secondaryPhoneNumber, String
                                   addressBookName) throws InvalidAddressBookDataException {
        Contact contact = new Contact(name,primaryPhoneNumber,
                secondaryPhoneNumber,addressBookName);
        if(addressBookContacts==null) {
            throw new IllegalArgumentException("Please pass valid address " +
                    "book contacts list.");
        }
        boolean contactAlreadyExists = addressBookContacts.stream()
                .anyMatch(contactItem -> contactItem.getName()
                        .equalsIgnoreCase(name) && contactItem
                        .getAddressBookName().equalsIgnoreCase(addressBookName));
        if(contactAlreadyExists) {
            throw new InvalidAddressBookDataException("Contact with same " +
                    "name already exists in this address book");

        }else
            addressBookContacts.add(contact);
    }


    public boolean removeContact(List<Contact> contacts, String
            contactName){
        return contacts.removeIf(contact -> {
            return contact.getName().equalsIgnoreCase(contactName);
        });
    };


    public void printUniqueContacts(List<Contact> addressBookContacts, PrintStream printStream){
        printContacts(addressBookContacts.parallelStream().distinct().collect(Collectors.toList()),
                printStream);
    };

    public void printContacts(List<Contact> contacts, PrintStream printStream) {

        Map<String, List<Contact>> groupedContacts =
                contacts.stream().collect(Collectors.groupingBy(w -> w
                        .getAddressBookName()));
        groupedContacts.forEach((k,v)->{ printStream.println("Address Book " +
                "Name:" + k + "\nContacts:" );
            v.forEach(i->{printStream.println(i);});
        });
    }

}
