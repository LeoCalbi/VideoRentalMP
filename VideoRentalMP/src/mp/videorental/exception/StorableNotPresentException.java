package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class StorableNotPresentException extends IOException {

	public StorableNotPresentException(String message) {
		super(message);
	}
	
	public StorableNotPresentException() {
		super("Storable not present in the repository.");
	}

}
