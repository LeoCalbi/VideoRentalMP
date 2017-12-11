package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class AlreadyRentedException extends IOException {
	
	public AlreadyRentedException(String message) {
		super(message);
	}
	
	public AlreadyRentedException() {
		super("The Rentable item is already rented.");
	}
	
}
