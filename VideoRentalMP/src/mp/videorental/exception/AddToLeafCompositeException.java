package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class AddToLeafCompositeException extends IOException {
	
	public AddToLeafCompositeException(String message) {
		super(message);
	}
	
	public AddToLeafCompositeException() {
		super("Cannot add an item to a leaf.");
	}
	
}
