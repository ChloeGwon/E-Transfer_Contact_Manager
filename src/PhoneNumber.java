
public class PhoneNumber extends Customer {

	private String phoneNumber;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(String n, String p) {
		super(n);
		phoneNumber = p;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneException {
		//If phoneNumber is not 10 digits, throw an exception
		if (phoneNumber.length() != 10)
			throw new InvalidPhoneException();
		
		boolean digits = true;
		//If phoneNumber is not digit, throw an exception
		for (int i=0; i<phoneNumber.length(); i++) {
			if (!Character.isDigit(phoneNumber.charAt(i)))
				digits = false;
		}
		
		if (!digits)
			throw new InvalidPhoneException();
		else
			this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
		super.str = super.toString() + "\n Phone number: " + phoneNumber;
		return str;
	}
}
