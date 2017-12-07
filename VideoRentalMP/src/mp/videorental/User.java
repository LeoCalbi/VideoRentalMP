package mp.videorental;

public abstract class User extends Person {
	
	private Credentials credentials;
	
	public boolean access(Credentials c) {
		return c.equals(credentials);
	}
	
}
