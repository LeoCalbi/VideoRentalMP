package mp.videorental;

import java.time.LocalDate;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class Director extends Person {
	
	private static final long serialVersionUID = 3199925935959912562L;

	//TODO controllare struttura per codice fiscale
	public Director(String name, String surname, LocalDate birthday) {
		super("", name, surname, birthday);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Director) {
			Director other = (Director) obj;
			if(getName().equals(other.getName()) && getSurname().equals(other.getSurname())) return true;
		}
		return false;
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		DirectorRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		DirectorRepository.getInstance().remove(this, admin);
	}
	
}
