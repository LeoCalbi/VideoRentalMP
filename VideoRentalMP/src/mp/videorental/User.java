package mp.videorental;
import java.time.LocalDate;

public abstract class User extends Person {
	
	private Credentials credentials;
	
	public User(String name, String surname, LocalDate birthday) {
		super(name, surname, birthday);
	}
	
	public boolean access(Credentials c) {
		return c.equals(credentials);
	}
	
}
