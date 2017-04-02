package com.nav.reece.io.reader;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import com.nav.reece.constants.AddressBookConfigConstants;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AddressBookFileReader implements DataReader {

    private String dataFilePath;

    public AddressBookFileReader() {
    }

    public AddressBookFileReader(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public List<String> readData() throws
            InvalidAddressBookDataException, IOException {
        BufferedReader bufferedReader = null;
        List<String> dataEntries = new ArrayList<>();
        try {
            InputStream in = getClass().getResourceAsStream("/" +
                    AddressBookConfigConstants.DEFAULT_DATA_FILE_PATH);
            if(dataFilePath==null) {
                bufferedReader = new BufferedReader(new InputStreamReader(in));
            }else {
                bufferedReader = new BufferedReader(new FileReader(dataFilePath));
            }
            Pattern regexPattern = Pattern.compile(AddressBookConfigConstants.CONTACT_DATA_CSV_VALIDITY_REGEX_PATTERN);
            dataEntries = bufferedReader.lines().collect(Collectors.toList());
            if (!dataEntries.isEmpty() && !(dataEntries.stream().allMatch
                    (regexPattern
                    .asPredicate()))) {
                throw new InvalidAddressBookDataException("Invalid entries found " +
                        "in address book source file");
            }
        }
        finally {
            if(bufferedReader!=null) bufferedReader.close();
        }
        return dataEntries;
    }
}
