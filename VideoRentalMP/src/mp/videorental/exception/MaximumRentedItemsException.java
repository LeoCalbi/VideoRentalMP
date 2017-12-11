package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class MaximumRentedItemsException extends IOException {
	
	public MaximumRentedItemsException(String message) {
		super(message);
	}
	
	public MaximumRentedItemsException() {
		super("The Rented list is already full.");
	}
	
}
