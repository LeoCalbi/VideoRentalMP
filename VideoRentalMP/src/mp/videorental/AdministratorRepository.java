package mp.videorental;

import java.time.LocalDate;
import java.util.function.Predicate;

import mp.videorental.exception.InvalidAdminException;
import mp.videorental.exception.NotRemovableAdminException;
import mp.videorental.exception.StorableAlreadyPresentException;
import mp.videorental.exception.StorableNotPresentException;

public class AdministratorRepository extends Repository<Administrator> {

	private static final long serialVersionUID = 8326750509458853136L;
	private static AdministratorRepository instance;
	private static final Administrator defaultAdmin = new Administrator("123", "admin", "admin", LocalDate.of(1980, 6, 5), new Credentials("admin", "admin"));
	
	private AdministratorRepository() {
		super();
	}
	
	public static AdministratorRepository getInstance() {
		if(instance == null) {
			AdministratorRepository repo = StorableHandler.getInstance().readAdministrator();
			if(repo == null) {
				instance = new AdministratorRepository();
				try {
					instance.add(defaultAdmin,defaultAdmin);
				} catch (InvalidAdminException | StorableAlreadyPresentException e) {}
			} else instance = repo;
		}
		return instance;
	}
	
	@Override
	public void remove(Administrator item, Administrator admin) throws InvalidAdminException, StorableNotPresentException {
		if(item.equals(defaultAdmin)) throw new NotRemovableAdminException();
		super.remove(item, admin);
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeAdministrator();
	}

	@Override
	public void removeIf(Predicate<? super Administrator> filter) {
		Predicate<Administrator> defaultFilter = (Administrator a) -> !a.equals(defaultAdmin);
		defaultFilter.and(filter);
		super.removeIf(defaultFilter);
	}
	
}