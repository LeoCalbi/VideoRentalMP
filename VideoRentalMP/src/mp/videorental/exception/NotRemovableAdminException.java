package mp.videorental.exception;

@SuppressWarnings("serial")
public class NotRemovableAdminException extends StorableNotPresentException {

	public NotRemovableAdminException(String message) {
		super(message);
	}
	
	public NotRemovableAdminException() {
		super("Cannot remove this admin.");
	}

}
