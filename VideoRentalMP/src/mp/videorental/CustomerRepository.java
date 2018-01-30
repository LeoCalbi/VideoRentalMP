package mp.videorental;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CustomerRepository extends Repository<Customer> {
	
	private static final long serialVersionUID = -5504650075488925954L;
	private static CustomerRepository instance = new CustomerRepository();
	
	private CustomerRepository() {
		try {
			ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("data/Customer",false));
			out.writeObject(instance);
			out.close();
		} catch(Exception e) {}
	}
	
	public CustomerRepository(CustomerRepository instance) {
		CustomerRepository.instance = instance;
	}
	
	public static CustomerRepository getInstance() throws FileNotFoundException, ClassNotFoundException, IOException {
		 if(instance == null) StorableHandler.getInstance().read();
		 return instance;
	}

}