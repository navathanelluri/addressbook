package com.nav.reece;

import com.nav.reece.Exception.InvalidAddressBookDataException;
import org.junit.Test;
import org.mockito.Mockito;

public class ApplicationTest {
    @Test
    public void testApplicationMainRunsAddressBookCommandProcessor() throws
            Exception{
        //given
        String[] args = {};
        CommandProcessor commandProcessor = Mockito
                .mock(CommandProcessor.class);
        Application.setCommandProcessor
                (commandProcessor);;
        //when
        Application.main(args);
        //then
        Mockito.verify(commandProcessor).run(args);
    }

    @Test
    public void testHandlesExceptionFromRunningAddressBookCommandProcessor()
            throws Exception{
        //given
        String[] args = {};
        CommandProcessor commandProcessor = Mockito
                .mock(CommandProcessor.class);
        InvalidAddressBookDataException invalidAddressBookDataException =
                Mockito.mock(InvalidAddressBookDataException.class);
        Mockito.doThrow(invalidAddressBookDataException).when
                (commandProcessor).run(args);
        Application.setCommandProcessor(commandProcessor);
        //when
        Application.main(args);
        //then
        Mockito.verify(commandProcessor).run(args);
        Mockito.verify(invalidAddressBookDataException).printStackTrace();
    }

}