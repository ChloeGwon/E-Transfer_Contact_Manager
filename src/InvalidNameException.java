
public class InvalidNameException extends Exception {

	public InvalidNameException() {
		super("Name should not have digits or special characters.");
	}
}
