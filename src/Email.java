
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
		boolean atSign = false;
		for (int i=0; i<email.length(); i++) {
			if (email.charAt(i) == '@') {
				atSign = true;
				break;
			}
		}
		
		if (atSign)
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
