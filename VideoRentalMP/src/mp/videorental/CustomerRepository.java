package mp.videorental;

import mp.videorental.exception.RepositoryNotInitializedException;

@SuppressWarnings("serial")
public class CustomerRepository extends Repository<MovieBox> {
	
	private static CustomerRepository instance = new CustomerRepository();
	
	private CustomerRepository() {}
	
	public CustomerRepository(CustomerRepository instance) {
		CustomerRepository.instance=instance;
	}
	
	public static CustomerRepository getInstance() throws RepositoryNotInitializedException {
		 if(instance == null) throw new RepositoryNotInitializedException();
		 return instance;
	}

}