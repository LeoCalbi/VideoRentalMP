package mp.videorental;
import java.time.LocalDate;

public class StandardCustomer extends Customer {

	public StandardCustomer(String socialSecurityNumber,String name, String surname, LocalDate birthday, String address, String telephone, Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday, address, telephone, credentials);
	}

	@Override
	public CustomerCard makeCustomerCard() {
		return new StandardCard();
	}
	
}
