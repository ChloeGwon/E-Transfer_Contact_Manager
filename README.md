## Description ##
This is a terminal application that manages E-Transfer contact information.
You can add a new contact with email or phone number and remove a contact from the list.

## How to Run the Project ##
* Download Visual Studio Code.
* In Visual Studio Code, click on Extensions and download Debugger for Java.
* Click on View, Command Palette, search Git: Clone, and paste https://github.com/ChloeGwon96/E-Transfer
* Under src folder, click on CustomerDatabase.java to open.
* Click on Run, and Run Without Debugging.

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
![E-Transfer](https://user-images.githubusercontent.com/96569864/150075382-d7231ea5-1d08-40a9-8882-ad64968b7ad0.png)
