package mp.videorental;
import java.time.LocalDate;

public final class Administrator extends User {

	public Administrator(String socialSecurityNumber,String name, String surname, LocalDate birthday, Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday, credentials);
	}
	//TODO aggiungere add e remove Storable dopo revisione
}
