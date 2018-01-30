package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.NotRemovableAdminException;
import mp.videorental.exception.StorableNotPresentException;

public class AdministratorRepository extends Repository<Administrator> {

	private static final long serialVersionUID = 8326750509458853136L;
	private static AdministratorRepository instance;
	private final Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	
	public AdministratorRepository(AdministratorRepository instance) {
		AdministratorRepository.instance = instance;
	}
	
	public static AdministratorRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}
	
	@Override
	public void remove(Administrator item, Administrator admin) throws InvalidAdminException, StorableNotPresentException, FileNotFoundException, IOException, ClassNotFoundException {
		if(item.equals(defaultAdmin)) throw new NotRemovableAdminException();
		super.remove(item, admin);
	}

}