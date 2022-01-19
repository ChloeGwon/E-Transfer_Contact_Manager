# E-Transfer #
## Description ##
This is a terminal application that manages E-Transfer contact information.
You can add a new contact with email or phone number and remove a contact from the list.

## How to Run the Project ##
Download Visual Studio Code.
In Visual Studio Code, click on Extensions and download Debugger for Java.
Click on View, Command Palette, search Git: Clone, and paste https://github.com/ChloeGwon96/E-Transfer
Under src folder, click on CustomerDatabase.java to open.
Click on Run, and Run Without Debugging.

## Features ##
* It gives error 
    * when it prompts a character to proceed and you do not enter the appropriate characters
    * when the name you have entered matches an existing contact in your list
    * when the name you have entered has digits or special characters
    * when the email you have entered does not have an at sign and dot sign
    * when the phone number you have entered are not numeric values
    * when you delete and the id you have entered does not exist or is not numeric values
* The memory is volatile.
* Names are saved in all capital letters.
* When it prompts a character to proceed, it is not case sensitive.

## Tests ##
```
Do you want to Add or Delete a contact to E-transfer
Enter 'A' to add, 'D' to delete or 'Q' to quit
**a**
Enter the contact's name
**Chloe**
Do you want to add an email or phone number?
Enter 'E' for email or 'P' for phone number.
**e**
Enter the contact's email address
**chgmlk@gmail.com**
Account number #1
 CHLOE
 Email: chgmlk@gmail.com

Do you want to Add or Delete a contact to E-transfer
Enter 'A' to add, 'D' to delete or 'Q' to quit
**A**
Enter the contact's name
**ben**
Do you want to add an email or phone number?
Enter 'E' for email or 'P' for phone number.
**p**
Enter only digits for the contact's phone number
**7789990000**
Account number #1
 CHLOE
 Email: chgmlk@gmail.com

Account number #2
 BEN
 Phone number: 778-999-0000

Do you want to Add or Delete a contact to E-transfer
Enter 'A' to add, 'D' to delete or 'Q' to quit
**d**
Enter the contact's account number you want to delete
**1**
Successfully deleted!
Account number #1
 BEN
 Phone number: 778-999-0000

Do you want to Add or Delete a contact to E-transfer
Enter 'A' to add, 'D' to delete or 'Q' to quit
**q**
Thank you!
```
