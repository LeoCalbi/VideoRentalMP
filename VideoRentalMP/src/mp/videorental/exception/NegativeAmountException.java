package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class NegativeAmountException extends IOException {
	
	public NegativeAmountException(String message) {
		super(message);
	}
	
	public NegativeAmountException() {
		super("The inserted amount is a negative number.");
	}
	
}
