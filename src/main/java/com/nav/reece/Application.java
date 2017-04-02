package com.nav.reece;

public class Application {
    private static CommandProcessor commandProcessor = new CommandProcessor(System.in,System
            .out,new AddressBookManager());

    public static void main(String[] args) {
        try {
            commandProcessor.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void setCommandProcessor(CommandProcessor commandProcessor){
        Application.commandProcessor = commandProcessor;
    }
}
