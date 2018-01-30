package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class InvalidAdminException extends IOException {

	public InvalidAdminException(String message) {
		super(message);
	}
	
	public InvalidAdminException() {
		super("The admin is not a valid admin.");
	}

}
