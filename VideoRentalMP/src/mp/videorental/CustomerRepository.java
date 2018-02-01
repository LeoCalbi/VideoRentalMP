package mp.videorental;

public class CustomerRepository extends Repository<Customer> {
	
	private static final long serialVersionUID = -5504650075488925954L;
	private static CustomerRepository instance;
	
	private CustomerRepository() {
		super();
	}
	
	public static CustomerRepository getInstance() {
		if(instance == null) {
			CustomerRepository repo = StorableHandler.getInstance().readCustomer();
			if(repo == null) instance = new CustomerRepository();
			else instance = repo;
		}
		return instance;
	}

	@Override
	public void write() {
		StorableHandler.getInstance().writeCustomer();
	}

}