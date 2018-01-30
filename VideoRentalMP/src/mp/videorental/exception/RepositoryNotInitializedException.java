package mp.videorental.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class RepositoryNotInitializedException extends IOException {

	public RepositoryNotInitializedException(String message) {
		super(message);
	}
	
	public RepositoryNotInitializedException() {
		super("Repository not initialized");
	}

}
