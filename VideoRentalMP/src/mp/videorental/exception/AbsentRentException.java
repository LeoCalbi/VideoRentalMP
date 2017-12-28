package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class AbsentRentException extends IOException {
	
	public AbsentRentException(String message) {
		super(message);
	}
	
	public AbsentRentException() {
		super("The searched Rentable item is not present in the Rent list.");
	}
	
}
