
public class Customer {

	protected String name = "";
	protected String str = "";
	
	public Customer() {
		
	}
	
	public Customer(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) throws InvalidNameException {
		//Throw an exception if name has digits or special characters
		boolean digit = false;
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i)) || !Character.isLetter(name.charAt(i))) {
				digit = true;
				break;
			}
		}
		
		if (digit)
			throw new InvalidNameException();
		else
			this.name = name;
	}
	
	public String toString() {
		//Make letters to upper letter
		return name.toUpperCase();
	}
}
