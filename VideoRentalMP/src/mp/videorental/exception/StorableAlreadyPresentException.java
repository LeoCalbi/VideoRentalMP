package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class StorableAlreadyPresentException extends IOException {

	public StorableAlreadyPresentException(String message) {
		super(message);
	}
	
	public StorableAlreadyPresentException() {
		super("Storable already present in the repository.");
	}

}
