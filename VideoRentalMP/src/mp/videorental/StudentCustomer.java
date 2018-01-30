package mp.videorental;
import java.time.LocalDate;

public class StudentCustomer extends Customer {

	private static final long serialVersionUID = 1266198077404878145L;

	public StudentCustomer(String socialSecurityNumber,String name, String surname, LocalDate birthday, String address, String telephone, Credentials credentials) {
		super(socialSecurityNumber,name, surname, birthday, address,telephone, credentials);
	}

	@Override
	public CustomerCard makeCustomerCard() {
		return new StudentCard();
	}
	
}
