package mp.videorental;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerRepository extends Repository<Customer> {
	
	private static final long serialVersionUID = -5504650075488925954L;
	private static CustomerRepository instance;
	
	private CustomerRepository() {
		super();
	}
	
	public static CustomerRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(instance == null) {
			CustomerRepository repo = StorableHandler.getInstance().readCustomer();
			if(repo == null) instance = new CustomerRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() throws FileNotFoundException, ClassNotFoundException, IOException {
		StorableHandler.getInstance().writeCustomer();
	}

}