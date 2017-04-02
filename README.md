BRANCH MANAGER ADDRESS BOOK Coding Challenge
============================================

Challenge statement
-------------------

As a Reece Branch Manager
I would like an address book application on my PC
So that I can keep track of my customer contacts

Acceptance Criteria
o Address book will hold name and phone numbers of contact entries
o Users should be able to add new contact entries
o Users should be able to remove existing contact entries
o Users should be able to print all contacts in an address book
o Users should be able to maintain multiple address books
o Users should be able to print a unique set of all contacts across multiple address books


Deliverables
-----------
•  Written in Java
•  A working user interface is not required, nor the use of any frameworks.
•  We are looking for all acceptance criteria to be demonstrable through tests


Solution
--------
This is a simple Java based cli application that demonstrates Test Driven 
Development. This code does not depend on any third party libraries and 
frameworks other than JUnit and Mockito for test scope. 

Design
------

Some of the design considerations:  
•  Application follows OOP and KISS principles and every class is modular, 
simple, built for a single purpose, easily testable and readable  
• Clear and concise naming conventions are used both in source and tests for 
ease of understanding even without much java doc  
•  No GUI has been used as its not a requirement  
•  Java 8 patterns make the code easy to read, faster and fit well for 
testing with TDD.  
•  Entry to the application is through the Application main method that does 
very little and delegates off to CommandProcessor. This enables ease of 
testing of Application class and serves the purpose of the Application main 
method.  
•  Used Mockito for mocking dependencies to ensure faster test execution and 
to concentrate on a single unit of functionality 

Requirements
------------
• Java 8 
• Maven 3.3.9

How to build and run the application
------------------------------------
• git clone source from github - `git clone `     
• run `cd addressbook`  
• build `mvn clean install`  
• run `java -jar target\address-book-1.0-SNAPSHOT.jar`
