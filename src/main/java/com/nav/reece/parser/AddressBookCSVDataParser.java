package com.nav.reece.parser;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.constants.AddressBookConfigConstants;
import com.nav.reece.io.reader.AddressBookFileReader;
import com.nav.reece.io.reader.DataReader;
import com.nav.reece.io.writer.AddressBookFileWriter;
import com.nav.reece.io.writer.DataWriter;
import com.nav.reece.model.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVDataParser implements DataParser {

    private DataReader addressBookFileReader;
    private DataWriter addressBookFileWriter;

    public AddressBookCSVDataParser() {
        addressBookFileReader = new AddressBookFileReader();
        addressBookFileWriter = new AddressBookFileWriter();
    }

    public AddressBookCSVDataParser(DataReader
                                            addressBookFileReader, DataWriter
            addressBookFileWriter) {
        if (addressBookFileReader == null)
            throw new IllegalArgumentException("AddressBookFileReader is " +
                    "mandatory");
        this.addressBookFileReader = addressBookFileReader;
        if (addressBookFileWriter == null)
            throw new IllegalArgumentException("AddressBookFileWriter is " +
                    "mandatory");
        this.addressBookFileWriter = addressBookFileWriter;
    }

    public List<Contact> readFromFileAndParse() throws InvalidAddressBookDataException,IOException {
        List<String> contactsDataFromFile = addressBookFileReader.readData();
        List<Contact> contacts = new ArrayList<>();
        for (String contactDataLine : contactsDataFromFile) {
            String[] contactDetails = contactDataLine.split
                    (AddressBookConfigConstants.COMMA_DELIMITER);
            if (contactDetails.length > 0) {
                //create contact object from these details in string line
                Contact contact = new Contact(contactDetails[0], contactDetails[1],
                        contactDetails[2], contactDetails[3]);
                contacts.add(contact);
            }
        }
        return contacts;
    }

    @Override
    public void parseAndSaveToFile(List<Contact> contacts) throws
            InvalidAddressBookDataException, IOException{
        List<String> contactDetailsData  = new ArrayList<String>();
        for (Contact contact : contacts) {
            StringBuilder contactsCSVBuilder = new StringBuilder();
            contactsCSVBuilder.append(contact.getName()).append
                    (AddressBookConfigConstants.COMMA_DELIMITER);
            contactsCSVBuilder.append(contact.getPrimaryPhoneNumber()).append
                    (AddressBookConfigConstants.COMMA_DELIMITER);
            contactsCSVBuilder.append(contact.getSecondaryPhoneNumber())
                    .append(AddressBookConfigConstants.COMMA_DELIMITER);
            contactsCSVBuilder.append(contact.getAddressBookName());
            contactDetailsData.add(contactsCSVBuilder.toString());
        }
        addressBookFileWriter.writeData(contactDetailsData);

    }
}

