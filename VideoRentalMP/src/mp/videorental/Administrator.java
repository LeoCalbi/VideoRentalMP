package mp.videorental;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public final class Administrator extends User {

	private static final long serialVersionUID = -6742438872406102470L;

	public Administrator(String socialSecurityNumber,String name, String surname, LocalDate birthday, Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday, credentials);
	}
	
	@Override
	public void add(Administrator admin) throws InvalidAdminException, StorableAlreadyPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		AdministratorRepository.getInstance().add(this, admin);
	}

	@Override
	public void remove(Administrator admin) throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		AdministratorRepository.getInstance().remove(this, admin);
	}
	
}
