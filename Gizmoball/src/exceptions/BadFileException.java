package exceptions;

@SuppressWarnings("serial")
public class BadFileException extends Exception {

	public BadFileException(String err) {
		super(err);
	}

}