package mp.videorental;
import java.time.LocalDate;

public class Director extends Person {
	//TODO controllare struttura per codice fiscale
	public Director(String name, String surname, LocalDate birthday) {
		super("",name, surname, birthday);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Director) {
			Director other = (Director) obj;
			if(getName().equals(other.getName()) && getSurname().equals(other.getSurname())) return true;
		}
		return false;
	}
}
