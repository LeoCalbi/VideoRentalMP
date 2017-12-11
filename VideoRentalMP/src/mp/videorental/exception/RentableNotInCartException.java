package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class RentableNotInCartException extends IOException {
	
	public RentableNotInCartException(String message) {
		super(message);
	}
	
	public RentableNotInCartException() {
		super("The searched Rentable item is not present in the Rent list.");
	}
	
}
