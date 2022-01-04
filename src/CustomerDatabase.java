import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerDatabase {

	public static void main(String[] args) throws InvalidNameException, InvalidEmailException, InvalidPhoneException {
		
		Scanner input = new Scanner(System.in);
		String name, contactEmail, contactPhoneNumber;
		char method;
		boolean noDigits = false, invalidMethod = false, invalidEmail = false, invalidPhoneNumber = false;
		int index = 0;
		
		//I made 4 arrays to save names, emails, and phone numbers individually. Because it's easier when I want to check if it exists.
		//'output' is for displaying output using toString method.
		String[] output = new String[100];
		String[] names = new String[100];
		String[] emails = new String[100];
		String[] phoneNumbers = new String[100];
		//I made integer count variable for each array. Because it should be implemented each time array is updated.
		int outputCount = 0, nameCount = 0, emailCount = 0, phoneCount = 0;
		
		Customer customer = new Customer();
		Email email = new Email();
		PhoneNumber phone = new PhoneNumber();
		
		
		System.out.println("Do you want to Add or Delete a contact to E-transfer");
		System.out.println("Enter 'A' to add, 'D' to delete or 'Q' to quit");
		char option = input.nextLine().charAt(0);
		
		while (option != 'Q' || option != 'q') {
			if (option == 'A' || option == 'a') {
				System.out.println("Enter the contact's name");
				name = input.nextLine();
				
				//I don't write System.out.println(e.getMessage) in catch block. 
				//Because it only gives error at first when error should be given every time name format is wrong. 
				try { 
					customer.setName(name); 
				}
				catch (InvalidNameException e) {
					
				};
				setName(input, names, nameCount, name, customer, email);
				nameCount++;

				System.out.println("Do you want to add an email or phone number?");
				System.out.println("Enter 'E' for email or 'P' for phone number.");
				method = input.nextLine().charAt(0);
				
				while (!invalidMethod) {
					if (method == 'E' || method == 'e') {
						email.setName(customer.getName());
						System.out.println("Enter the contact's email address");
						contactEmail = input.nextLine();
						
						setEmailAddress(input, emailCount, emails, email, contactEmail);
						//I set the name, contactEmail fields in main method because 'setEmailAddress' method returns email array.
						emailCount++;
						//We should implement phoneCount as well. If not, they will be different than outputCount.
						phoneNumbers[phoneCount] = "";
						phoneCount++;
						output[outputCount] = email.toString();
						outputCount++; 
						
						invalidMethod = true;
					}
					
					else if (method == 'P' || method == 'p') {
						phone.setName(customer.getName());
						System.out.println("Enter only digits for the contact's phone number");
						contactPhoneNumber = input.nextLine();
						
						setPhoneNumber(input, phoneCount, phoneNumbers, contactPhoneNumber, phone);
						phoneCount++; 
						//We should implement phoneCount as well. If not, they will be different than outputCount.
						emails[emailCount] = "";
						emailCount++;
						output[outputCount] = phone.toString();
						outputCount++;

						invalidMethod = true;
					}
					
					else {
						System.out.println("Invalid. Please enter 'E' or 'P'");
						method = input.nextLine().charAt(0);
					}
				}
				//set invalidMethod false out of the while loop so that the user is prompted to enter email or phone number
				invalidMethod = false;
				invalidEmail = false;
				invalidPhoneNumber = false;
			}
			
			else if (option == 'D' || option == 'd') {
				if (outputCount == 0)
					System.out.println("The database is empty");
				
				else {
					System.out.println("Enter the contact's account number you want to delete");
					String accountToDelete = input.nextLine();
					
					accountDelete(input, accountToDelete, outputCount, nameCount, emailCount, phoneCount, output, names, emails, phoneNumbers);
					outputCount--;
					nameCount--;
					emailCount--;
					phoneCount--;
					
					System.out.println("Successfully deleted!");
				}
			}
			
			else if (option == 'Q' || option == 'q') {
				System.out.println("Thank you!");
				System.exit(0);
			}
			
			//show all customers
			for (int i=0; i<outputCount; i++) {
				System.out.println("Account number #"+(index+i+1));
				System.out.println(" "+output[i]+"\n");
			}
			
			System.out.println("Do you want to Add or Delete a contact to E-transfer");
			System.out.println("Enter 'A' to add, 'D' to delete or 'Q' to quit");
			option = input.nextLine().charAt(0);
		}
	}
	
	public static void setName(Scanner input, String[] names, int nameCount, String name, Customer customer, Email email) throws InvalidNameException {
		
		//Check if this name already exists
		boolean nameExist = false;
		if (nameCount > 0) {
			for (int i=0; i<nameCount; i++) {
				if (names[i].equals(name.toUpperCase()))
					nameExist = true;
			}
		}
		
		while (nameExist) {
			System.out.println("The name you have entered matches an existing contact in your list.");
			System.out.println("Enter the contact's name");
			name = input.nextLine();
			
			if (nameCount > 0) {
				for (int i=0; i<nameCount; i++) {
					if (names[i].equals(name.toUpperCase()))
						nameExist = true;
					else
						nameExist = false;
				}
			}
		}
		
		boolean digitOrLetter = false;
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i)) || !Character.isLetter(name.charAt(i))) {
				digitOrLetter = true;
				break;
			}
			else
				digitOrLetter = false;
		}
		
		while (digitOrLetter) {
			System.out.println("Name should not have digits or special characters.");
			System.out.println("Enter the contact's name");
			name = input.nextLine();
			
			try {
				//Set the name in the customer class
				customer.setName(name);
				//Put the name in names array
				names[nameCount] = customer.toString();

				//to get out of this while loop
				digitOrLetter = false;
			}
			catch (InvalidNameException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter the contact's name");
				name = input.nextLine();
				
				for (int i=0; i<name.length(); i++) {
					if (Character.isDigit(name.charAt(i)) || !Character.isLetter(name.charAt(i))) {
						digitOrLetter = true;
						break;
					}
					else {
						
						digitOrLetter = false;
					}
				}
			}
		}
		customer.setName(name);
		names[nameCount] = customer.toString();
	}
	
	public static void setEmailAddress(Scanner input, int emailCount, String[] emails, Email email, String contactEmail) {
		
		//Check if this email already exists
		boolean emailExist = false;
		if (emailCount > 0) {
			for (int i=0; i<emailCount; i++) {
				if (emails[i].equals(contactEmail))
					emailExist = true;
			}
		}
		
		while (emailExist) {
			System.out.println("The email address you have entered matches an existing contact in your list.");
			System.out.println("Enter the contact's email address");
			contactEmail = input.nextLine();
			
			if (emailCount > 0) {
				for (int i=0; i<emailCount; i++) {
					if (emails[i].equals(contactEmail))
						emailExist = true;
					else
						emailExist = false;
				}
			}
		}
		
		//If email format is not valid, give an exception and keep asking
		boolean invalidEmailFormat = false;
		while (!invalidEmailFormat) {
			try {
				email.setEmail(contactEmail); 
				emails[emailCount] = contactEmail;
				invalidEmailFormat = true;
			}
			catch (InvalidEmailException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter the contact's email address");
				contactEmail = input.nextLine();
			}
		}
	}
	
	public static void setPhoneNumber(Scanner input, int phoneCount, String[] phoneNumbers, String contactPhoneNumber, PhoneNumber phone) {
		
		//Check if this phone number already exists
		boolean phoneExist = false;
		if (phoneCount > 0) {
			for (int i=0; i<phoneCount; i++) {
				if (phoneNumbers[i].equals(contactPhoneNumber)) {
					phoneExist = true;
				}
			}
		}
		
		while (phoneExist) {
			System.out.println("The phone number you have entered matches an existing contact in your list.");
			System.out.println("Enter the contact's phone number");
			contactPhoneNumber = input.nextLine();
			
			if (phoneCount > 0) {
				for (int i=0; i<phoneCount; i++) {
					if (phoneNumbers[i].equals(contactPhoneNumber)) {
						phoneExist = true;
					}
					else
						phoneExist = false;
				}
			}
		}
		
		//If phone number format is not valid, give an exception and keep asking
		boolean invalidPhoneNumber = false;
		while (!invalidPhoneNumber) {
			try {
				phone.setPhoneNumber(contactPhoneNumber);
				phoneNumbers[phoneCount] = contactPhoneNumber;
				invalidPhoneNumber = true;
			}
			catch (InvalidPhoneException e) {
				System.out.println(e.getMessage());
				System.out.println("Enter only digits for the contact's phone number");
				contactPhoneNumber = input.nextLine();
			}
		}
	}
	
	//The reason why I am calling 4 deleteFromArray methods in accountDelete method is because in order to use the latest input.
	//The return value of this accountDelete method is first input the user entered, not latest one.
	public static void accountDelete(Scanner input, String accountToDelete, int outputCount, int nameCount, int emailCount, int phoneCount,
			String[] output, String[] names, String[] emails, String[] phoneNumbers) {
		
		//I set the invalidAccountNbr boolean to be true. Because if I set it false initially and set it true when digit is found,
		//It won't find if the rest is a letter or special character.
		boolean invalidAccountNbr = true;
		
		while (invalidAccountNbr) {
			//If accountToDelete value is not digit, ask to enter again.
			boolean digits = true;
			for (int i=0; i<accountToDelete.length(); i++) {
				if (!Character.isDigit(accountToDelete.charAt(i)))
					digits = false;
			}
			
			if (digits) {
				if (Integer.parseInt(accountToDelete) > 0 && Integer.parseInt(accountToDelete) <= outputCount) {
					invalidAccountNbr = false;
					break;
				}
			}
			
			System.out.println("This account number does not exist.");
			System.out.println("Enter the contact's account number you want to delete");
			accountToDelete = input.nextLine();
		}
		
		deleteFromOutputArray(accountToDelete, output, outputCount);
		deleteFromNamesArray(accountToDelete, names, nameCount);
		deleteFromEmailsArray(accountToDelete, emails, emailCount);
		deleteFromPhonesArray(accountToDelete, phoneNumbers, phoneCount);
	}
	
	//I made 4 methods for each array because only one value can be returned.
	public static String[] deleteFromOutputArray(String accountToDelete, String[] output, int outputCount) {
		for (int i=(Integer.parseInt(accountToDelete)-1); i<outputCount-1; i++) {
			output[i] = output[i+1];
		}
		output[outputCount-1] = "";
		
		return output;
	}
	
	public static String[] deleteFromNamesArray(String accountToDelete, String[] names, int nameCount) {
		for (int i=(Integer.parseInt(accountToDelete)-1); i<nameCount-1; i++) {
			names[i] = names[i+1];
		}
		names[nameCount-1] = "";

		return names;
	}
	
	public static String[] deleteFromEmailsArray(String accountToDelete, String[] emails, int emailCount) {
		for (int i=(Integer.parseInt(accountToDelete)-1); i<emailCount-1; i++) {
			emails[i] = emails[i+1];
		}
		emails[emailCount-1] = "";

		return emails;
	}
	
	public static String[] deleteFromPhonesArray(String accountToDelete, String[] phoneNumbers, int phoneCount) {
		for (int i=(Integer.parseInt(accountToDelete)-1); i<phoneCount-1; i++) {
			phoneNumbers[i] = phoneNumbers[i+1];
		}
		phoneNumbers[phoneCount-1] = "";
		
		return phoneNumbers;
	}
	
}
