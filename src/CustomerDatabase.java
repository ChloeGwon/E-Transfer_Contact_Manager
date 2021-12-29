import java.util.Scanner;

public class CustomerDatabase {

	public static void main(String[] args) throws InvalidNameException, InvalidEmailException, InvalidPhoneException {
		
		Scanner input = new Scanner(System.in);
		String name;
		String contactEmail;
		String contactPhoneNumber;
		char method;
		boolean noDigits = false;
		boolean invalidMethod = false;
		boolean invalidEmail = false;
		boolean invalidPhoneNumber = false;
		int index = 0;

		//'output' is for displaying
		String[] output = new String[100];
		//'names', 'emails', 'phoneNumbers' are to check if it already exists
		String[] names = new String[100];
		String[] emails = new String[100];
		String[] phoneNumbers = new String[100];
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
				
				//Check if this name already exists
				boolean nameExist = false;
				if (nameCount > 0) {
					for (int i=0; i<nameCount; i++) {
						if (names[i].equals(name.toUpperCase())) {
							nameExist = true;
						}
					}
				}
				
				while (nameExist) {
					System.out.println("The name you have entered matches an existing contact in your list.");
					System.out.println("Enter the contact's name");
					name = input.nextLine();
					
					if (nameCount > 0) {
						for (int i=0; i<nameCount; i++) {
							if (names[i].equals(name.toUpperCase())) {
								nameExist = true;
							}
							else
								nameExist = false;
						}
					}
				}
				
				//Set noDigits to false again to check if last name has any digit
				noDigits = false;
				//Keep prompting if name has any digit
				while (!noDigits) {
					try {
						//Set the name in the customer class
						customer.setName(name);
						//Put the name in names array
						names[nameCount] = customer.toString();
						nameCount++;
						//to get out of this while loop
						noDigits = true;
					}
					catch (InvalidNameException e) {
						System.out.println(e.getMessage());
						System.out.println("Enter the contact's first name");
						name = input.nextLine();
					}
				}
				
				System.out.println("Do you want to add an email or phone number?");
				System.out.println("Enter 'E' for email or 'P' for phone number.");
				method = input.nextLine().charAt(0);
				
				while (!invalidMethod) {
					if (method == 'E' || method == 'e') {
						System.out.println("Enter the contact's email address");
						contactEmail = input.nextLine();
						
						//Check if this email already exists
						boolean emailExist = false;
						if (emailCount > 0) {
							for (int i=0; i<emailCount; i++) {
								if (emails[i].equals(contactEmail)) {
									emailExist = true;
								}
							}
						}
						
						while (emailExist) {
							System.out.println("The email address you have entered matches an existing contact in your list.");
							System.out.println("Enter the contact's email address");
							contactEmail = input.nextLine();
							
							if (emailCount > 0) {
								for (int i=0; i<emailCount; i++) {
									if (emails[i].equals(contactEmail)) {
										emailExist = true;
									}
									else
										emailExist = false;
								}
							}
						}
						
						//If email format is not valid, give an exception and keep asking
						while (!invalidEmail) {
							try {
								//to check if it has an error
								email.setEmail(contactEmail); 
								//if contactEmail does not have an error, set those 3 fields below in Email class
								email = new Email(name, contactEmail);
								emails[emailCount] = contactEmail;
								emailCount++;
								//We should implement phoneCount as well. If not, it will be different than outputCount.
								phoneNumbers[phoneCount] = "";
								phoneCount++;
								output[outputCount] = email.toString();
								outputCount++;
								
								
								//to get out of this while loop
								invalidMethod = true; 
								invalidEmail = true;
							}
							catch (InvalidEmailException e) {
								System.out.println(e.getMessage());
								System.out.println("Enter the contact's email address");
								contactEmail = input.nextLine();
							}
						}
					}
					
					else if (method == 'P' || method == 'p') {
						System.out.println("Enter only digits for the contact's phone number");
						contactPhoneNumber = input.nextLine();
						
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
						while (!invalidPhoneNumber) {
							try {
								phone.setPhoneNumber(contactPhoneNumber);
								phone = new PhoneNumber(name, contactPhoneNumber);
								phoneNumbers[phoneCount] = contactPhoneNumber;
								phoneCount++;
								//We should implement phoneCount as well. If not, it will be different than outputCount.
								emails[emailCount] = "";
								emailCount++;
								output[outputCount] = phone.toString();
								outputCount++;
								
								invalidMethod = true;
								invalidPhoneNumber = true;
							}
							catch (InvalidPhoneException e) {
								System.out.println(e.getMessage());
								System.out.println("Enter only digits for the contact's phone number");
								contactPhoneNumber = input.nextLine();
							}
						}
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
					int numToDelete = input.nextInt();
					
					while (numToDelete < 0 || numToDelete > outputCount) {
						System.out.println("This account number does not exist.");
						System.out.println("Enter the contact's account number you want to delete");
						numToDelete = input.nextInt();
					}
					//delete the account information from all arrays
					for (int i=(numToDelete-1); i<outputCount-1; i++) {
						output[i] = output[i+1];
					}
					output[outputCount-1] = "";
					outputCount--;
					
					for (int i=(numToDelete-1); i<nameCount-1; i++) {
						names[i] = names[i+1];
					}
					names[nameCount-1] = "";
					nameCount--;
					
					for (int i=(numToDelete-1); i<emailCount-1; i++) {
						emails[i] = emails[i+1];
					}
					emails[emailCount-1] = "";
					emailCount--;
					
					for (int i=(numToDelete-1); i<phoneCount-1; i++) {
						phoneNumbers[i] = phoneNumbers[i+1];
					}
					phoneNumbers[phoneCount-1] = "";
					phoneCount--;
					
					input.nextLine();
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
}
