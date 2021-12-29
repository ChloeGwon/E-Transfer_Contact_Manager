
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
		//Throw an exception if name has digits
		boolean digit = false;
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i))) {
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
		//Make first letter upper case and the others lower case
//		name.toUpperCase();
		
//		String name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return name.toUpperCase();
	}
}
