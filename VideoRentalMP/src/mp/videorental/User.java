package mp.videorental;
import java.time.LocalDate;

public abstract class User extends Person {
	
	private static final long serialVersionUID = 1872045297231354470L;
	private Credentials credentials;
	
	public User(String socialSecurityNumber, String name, String surname, LocalDate birthday, Credentials credentials) {
		super(socialSecurityNumber, name, surname, birthday);
		this.credentials = credentials;
	}
	
	public boolean access(Credentials c) {
		return c.equals(credentials);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User) obj;
			return user.access(credentials);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return credentials.hashCode();
	}
	
}
