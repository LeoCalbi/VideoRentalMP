package mp.videorental.exception;
import java.io.IOException;

@SuppressWarnings("serial")
public class InsufficientFundsException extends IOException {

	public InsufficientFundsException(String message) {
		super(message);
	}
	
	public InsufficientFundsException() {
		super("Not enough credit.");
	}
	
}
