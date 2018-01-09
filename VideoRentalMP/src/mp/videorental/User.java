package mp.videorental;
import java.time.LocalDate;

public abstract class User extends Person {
	
	private Credentials credentials;
	
	public User(String socialSecurityNumber,String name, String surname, LocalDate birthday,Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday);
		this.credentials=credentials;
	}
	
	public boolean access(Credentials c) {
		return c.equals(credentials);
	}
	
}
