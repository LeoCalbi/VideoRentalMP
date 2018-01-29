package mp.videorental.exception;

import java.io.IOException;

public class RepositoryNotInitializedException extends IOException {

	public RepositoryNotInitializedException() {
		super("Repository not initialized");
	}

	public RepositoryNotInitializedException(String arg0) {
		super(arg0);
	}

}
