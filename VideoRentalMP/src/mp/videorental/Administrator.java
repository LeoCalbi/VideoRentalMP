package mp.videorental;

import java.time.LocalDate;
import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public final class Administrator extends User {

	private static final long serialVersionUID = -6742438872406102470L;

	public Administrator(String socialSecurityNumber, String name, String surname, LocalDate birthday, Credentials credentials) {
		super(socialSecurityNumber, name, surname, birthday, credentials);
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException {
		AdministratorRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		AdministratorRepository.getInstance().remove(this, admin);
	}
	
}
