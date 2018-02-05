package mp.videorental;
import java.time.LocalDate;

public class SeniorCustomer extends Customer {

	private static final long serialVersionUID = -5131255463067507527L;

	public SeniorCustomer(String socialSecurityNumber, String name, String surname, LocalDate birthday, String address, String telephone, Credentials credentials) {
		super(socialSecurityNumber, name, surname, birthday, address, telephone, credentials);
	}

	@Override
	public CustomerCard makeCustomerCard() {
		return new SeniorCard();
	}
	
}
