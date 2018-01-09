package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class NotRentedException extends IOException {

	public NotRentedException(String message) {
		super(message);
	}

	public NotRentedException() {
		super("The Rentable item wasn't rented.");
	}
	
}
