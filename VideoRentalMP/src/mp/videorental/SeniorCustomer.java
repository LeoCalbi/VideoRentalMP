package mp.videorental;

import java.time.LocalDate;

public class SeniorCustomer extends Customer {

	public SeniorCustomer(String name, String surname, LocalDate birthday, String address, String telephone) {
		super(name, surname, birthday, address, telephone);
	}

	@Override
	public CustomerCard makeCustomerCard() {
		return new SeniorCard();
	}
	
}
