package com.nav.reece.model;

import com.sun.org.apache.xerces.internal.impl.dv.xs.FullDVFactory;

import java.util.Date;

public class Contact {
    private String name;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String addressBookName;

    public Contact(String name, String primaryPhoneNumber, String secondaryPhoneNumber, String addressBookName) {
        this.name = name;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        this.addressBookName = addressBookName;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public String getAddressBookName() {
        return addressBookName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", primaryPhoneNumber='" + primaryPhoneNumber + '\'' +
                ", secondaryPhoneNumber='" + secondaryPhoneNumber + '\'' +
                ", addressBookName='" + addressBookName + '\'' +
                '}';
    }
}
