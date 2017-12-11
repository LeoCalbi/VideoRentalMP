package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class EmptyRentListException extends IOException {

	public EmptyRentListException(String message) {
		super(message);
	}
	
	public EmptyRentListException() {
		super("The Rent list is empty.");
	}
	
}
