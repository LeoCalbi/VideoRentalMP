package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class AdministratorRepository extends Repository<Administrator> {
	
	private static AdministratorRepository instance = new AdministratorRepository();
	
	private AdministratorRepository() {}
	
	public AdministratorRepository(AdministratorRepository instance) {
		AdministratorRepository.instance=instance;
	}
	
	public static AdministratorRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}