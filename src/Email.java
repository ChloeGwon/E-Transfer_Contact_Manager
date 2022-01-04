
public class Email extends Customer {

	private String email;
	
	public Email() {
		
	}
	
	public Email(String n, String e) {
		super(n);
		email = e;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {
		//Throw an exception if email does not have at sign or dot symbol
		boolean atSign = false;
		for (int i=0; i<email.length(); i++) {
			if (email.charAt(i) == '@') {
				atSign = true;
				break;
			}
		}
		
		boolean dotSymbol = false;
		for (int i=0; i<email.length(); i++) {
			if (email.charAt(i) == '.') {
				dotSymbol = true;
				break;
			}
		}
		
		if (atSign && dotSymbol)
			this.email = email;
		else
			throw new InvalidEmailException();
	}
	
	@Override
	public String toString() {
		super.str = super.toString() + "\n Email: " + email;
		return str;
	}
}
