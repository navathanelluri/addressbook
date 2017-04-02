package com.nav.reece.io.writer;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.constants.AddressBookConfigConstants;
import com.nav.reece.model.Contact;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class AddressBookFileWriter implements DataWriter{
    private String dataFilePath = AddressBookConfigConstants.DEFAULT_DATA_FILE_PATH;

    public AddressBookFileWriter() {
    }

    public AddressBookFileWriter(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public boolean writeData(List<String> contacts) throws
            InvalidAddressBookDataException, IOException {
        BufferedWriter writer = null;
        try{
            //ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(getClass().getResource("/data.txt")
                    .getFile());
//
//            System.out.println("how"+file.getAbsolutePath());
            if(dataFilePath==null){
                dataFilePath = file.getAbsolutePath();
            }

            File dataFile = new File(dataFilePath);
            if (!dataFile.exists()){
                throw new InvalidAddressBookDataException("Address Book Data " +
                        "file could not be found");
            }
            writer = new BufferedWriter(new FileWriter(dataFile));

            //clear contents and then append
            for (String contact : contacts) {
                writer.append(contact.toString());
                writer.newLine();
            }
            return true;
        }
       finally
        {
            if (writer!=null) writer.close();
        }
    }
}
